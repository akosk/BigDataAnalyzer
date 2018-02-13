package hu.innocenter.bigdata.analyzer

import java.sql.{ResultSet, DriverManager}
import java.util
import java.util.StringTokenizer
import java.util.regex.{Matcher, Pattern}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.{SVMWithSGD, SVMModel}
import org.apache.spark.mllib.evaluation.RegressionMetrics
import org.apache.spark.mllib.feature.{PCAModel, StandardScaler, PCA}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.optimization._
import org.apache.spark.mllib.regression.{LinearRegressionWithSGD, LabeledPoint}
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer


/**
  * Created by akosk on 2016.02.05..
  */
class ScalaLinearRegressionCalculatorLBFGS extends Calculator {
  override def calculate(dataSource: String, sqlQuery: String, params: util.HashMap[String, AnyRef]): LinearRegressionResult = {


    Logger.getRootLogger.setLevel(Level.WARN)

    var fieldnames: Array[String] = params.get("fieldnames").asInstanceOf[Array[String]]
    var fieldnamesBeforePCA: Array[String] = params.get("fieldnames").asInstanceOf[Array[String]]

    //  SPARK SETUP
    val conf = new SparkConf().setAppName(s"LinearRegression").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlcontext = new SQLContext(sc)

    val myRDD = new JdbcRDD(sc, () =>
      DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata", "root", "start"),
      sqlQuery + " AND 0 >= ? AND 2 <= ?",
      0, 2, 1,
      (r: ResultSet) => {

        val columnCount = r.getMetaData.getColumnCount

        val x: Array[Double] = new Array[Double](columnCount - 1)

        for (i <- 1 to columnCount-1) {
          x(i - 1) = r.getDouble(i)
        }

        val y: Double = r.getDouble(columnCount)
        new LabeledPoint(y, Vectors.dense(x))

      }
    )

    val first: Array[LabeledPoint] = myRDD.take(1)
    var columnCount = first(0).features.size + 1


    val k = if (params.get("principal")!=null && params.get("principal").equals("true")) params.get("princpal_components").asInstanceOf[String].toInt else 0


    var trainingWithoutBias = myRDD.map(x => (x.label, x.features)).cache()
    var training = myRDD.map(x => (x.label, MLUtils.appendBias(x.features))).cache()
val collect4: Array[(Double, Vector)] = training.collect()
    var pca: PCAModel = null;
    var columnCountBeforePCA = columnCount;
    if (k > 0) {


      val scaler = new StandardScaler(withMean = true, withStd = true).fit(training.map(x => x._2))
      val training_scaled = training.map(x => (x._1, scaler.transform(Vectors.dense(x._2.toArray))))
      pca = new PCA(k).fit(training_scaled.map(_._2))
      trainingWithoutBias = training_scaled.map(p => p.copy(_2 = pca.transform(p._2)))
      training = trainingWithoutBias.map(x => (x._1, MLUtils.appendBias(x._2))).cache()

      val collect3: Array[(Double, Vector)] = trainingWithoutBias.collect()
      val collect: Array[(Double, Vector)] = training.collect()

      var fieldnames_new = new Array[String](k + 1)
      fieldnames_new(k) = fieldnames(0)
      fieldnames = fieldnames_new
      for (i <- 0 to k - 1) {
        fieldnames(i) = s"FK${i}"
      }

      columnCount = k+1
    }


//    val sgdTrainingData: RDD[LabeledPoint] = trainingWithoutBias.map(x=> new LabeledPoint(x._1,x._2))
//
//    val numIterations = 100
//    val stepSize = 0.00000001
//    val model2 = LinearRegressionWithSGD.train(sgdTrainingData, numIterations, stepSize)

    //  LBFGS
    val numCorrections = 5 //10//5//3
    val convergenceTol = 1e-10 //1e-4
    val maxNumIterations = 100000 //20//100
    val regParam = 0 //0.1//10.0

    val collect2: Array[(Double, Vector)] = training.collect()

//    val (weightsWithIntercept, loss) = LBFGS.runLBFGS(
//      training,
//      new HingeGradient(), //LeastSquaresGradient
//      new SimpleUpdater(), //SquaredL2Updater(),SimpleUpdater(),L1Updater()
//      10,
//      1e-4,
//      100,
//      0,
//      Vectors.zeros(columnCount)
//
//    val (weightsWithIntercept, loss) = LBFGS.runLBFGS(
//      training,
//  new LeastSquaresGradient(), //LeastSquaresGradient
//  new SquaredL2Updater(), //SquaredL2Updater(),SimpleUpdater(),L1Updater()
//      10,
//      1e-4,
//  50000,
//      0,
//      Vectors.zeros(columnCount)
//    )
    val (weightsWithIntercept, loss) = LBFGS.runLBFGS(
      training,
      new LeastSquaresGradient(), //LeastSquaresGradient
      new SquaredL2Updater(), //SquaredL2Updater(),SimpleUpdater(),L1Updater()
      numCorrections,
      convergenceTol,
      maxNumIterations,
      regParam,
      Vectors.zeros(columnCount)
    )

    val collect_training = training.collect()

    //  MODEL
    val model = new SVMModel(
      Vectors.dense(weightsWithIntercept.toArray.slice(0, weightsWithIntercept.size - 1)),
      weightsWithIntercept(weightsWithIntercept.size - 1))
    model.clearThreshold()

    val collect1: Array[(Double, Vector)] = trainingWithoutBias.collect()

    //    PREDICTIONS
    val valuesAndPredsRDD = trainingWithoutBias.map { point =>
      val prediction = model.predict(point._2)
      (prediction, point._1)
    }

    val collect = valuesAndPredsRDD.collect()
    val metrics = new RegressionMetrics(valuesAndPredsRDD)
    val valuesAndPreds: Array[(Double, Double)] = valuesAndPredsRDD.collect()

    //    REPORT
    val result = new LinearRegressionResult
    result.appendResultText("<h2>Linear regression with LBFGS</h2>");


    result.appendResultText("<table  class='table table-hover table-bordered table-condensed'><tr><th>Coefficient</th><th>Value</th></tr>")
    for (i <- 0 to weightsWithIntercept.toArray.size - 1) {
      val slice: Array[Double] = weightsWithIntercept.toArray.slice(i, i + 1)
      if (!slice.isEmpty) {
        val fieldname = if (i == fieldnames.size - 1) "intercept" else fieldnames(i)
        result.appendResultText(s"<tr><td>${fieldname}</td><td>${slice(0)}</td></tr>")
      }
    }
    result.appendResultText("</table>");

    if (pca != null) {
      val values: Array[Double] = pca.pc.values

      result.appendResultText("<table  class='table table-hover table-bordered table-condensed'><tr><td></td>")
      for (i <- 0 to k - 1) {
        result.appendResultText(s"<th>FK${i}</th>")
      }
      result.appendResultText("</tr>")


      for (i2 <- 0 to columnCountBeforePCA - 2) {
        result.appendResultText("<tr>")
        result.appendResultText(s"<th>${fieldnamesBeforePCA(i2)}</th>")
        for (i <- 0 to k - 1) {
          var value = values(i2 + i * columnCountBeforePCA);
          result.appendResultText(s"<td>${value}</td>")
        }
        result.appendResultText("</tr>")
      }
      result.appendResultText("</table>");

    }

    result.appendResultText("<table class='table table-hover table-bordered table-condensed'>")
    result.appendResultText(s"<tr><td>MSE (Mean squared error)</td><td>${metrics.meanSquaredError}</td></tr>")
    result.appendResultText(s"<tr><td>RMSE (Root mean squared error)</td><td>${metrics.rootMeanSquaredError}</td></tr>")
    result.appendResultText(s"<tr><td>R-squared</td><td>${metrics.r2}</td></tr>")
    result.appendResultText(s"<tr><td>MAE (Mean absolute error)</td><td>${metrics.meanAbsoluteError}</td></tr>")
    result.appendResultText(s"<tr><td>EXPVAL (Explained variance)</td><td>${metrics.explainedVariance}</td></tr>")
    result.appendResultText("</table>")

    result.appendResultText(s"<table class='table table-hover table-bordered table-condensed'><th>${fieldnamesBeforePCA(fieldnamesBeforePCA.size - 1)}</th><th>${fieldnamesBeforePCA(fieldnamesBeforePCA.size - 1)} (predicted)</th></tr>")
    for (e <- valuesAndPreds) {
      result.appendResultText(s"<tr><td>${e._2}</td><td>${e._1}</td></tr>")
      println(e._1)
    }

    result.appendResultText("</table>")

    sc.stop()

    return result
  }


}

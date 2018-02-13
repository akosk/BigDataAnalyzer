package hu.innocenter.bigdata.analyzer

import java.util

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.SVMModel
import org.apache.spark.mllib.feature.StandardScaler
import org.apache.spark.mllib.linalg.{DenseVector, Vectors}
import org.apache.spark.mllib.optimization.{LBFGS, SquaredL2Updater, LeastSquaresGradient}
import org.apache.spark.mllib.regression.{LinearRegressionWithSGD, LabeledPoint, LassoWithSGD}
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by akosk on 2016.02.05..
  */
class ScalaLinearRegressionCalculatorTestLBFGS extends Calculator {
  override def calculate(dataSource: String, sqlQuery: String, params: util.HashMap[String, AnyRef]): LinearRegressionResult = {

    Logger.getRootLogger.setLevel(Level.WARN)


    val conf = new SparkConf().setAppName(s"LinearRegression").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlcontext = new SQLContext(sc)





//    val p1: LabeledPoint = new LabeledPoint(2.0, Vectors.dense(1.0))
//    val p2: LabeledPoint = new LabeledPoint(3.0, Vectors.dense(2.0))
//    val p3: LabeledPoint = new LabeledPoint(4.0, Vectors.dense(3.0))
//val data = Array(p1, p2, p3)

    val data: ArrayBuffer[LabeledPoint] = ArrayBuffer()
    var a = 0;
    val r = scala.util.Random
    for( a <- 1 to 1000){
      val p: LabeledPoint = new LabeledPoint(a, Vectors.dense(a-1 + (r.nextDouble() - 0.5)))
      data += p
    }

    val labeledPointsRDD: RDD[LabeledPoint] = sc.parallelize(data, 1)

    val training = labeledPointsRDD.map(x => (x.label, MLUtils.appendBias(x.features))).cache()



    val numCorrections = 5 //10//5//3
    val convergenceTol = 1e-4 //1e-4
    val maxNumIterations = 100 //20//100
    val regParam = 0.001 //0.1//10.0

    val (weightsWithIntercept, loss) = LBFGS.runLBFGS(
      training,
      new LeastSquaresGradient(),//LeastSquaresGradient
      new SquaredL2Updater(), //SquaredL2Updater(),SimpleUpdater(),L1Updater()
      numCorrections,
      convergenceTol,
      maxNumIterations,
      regParam,
      Vectors.dense(0.0,0.0))//initialWeightsWithIntercept)


    val model = new SVMModel(
      Vectors.dense(weightsWithIntercept.toArray.slice(0, weightsWithIntercept.size - 1)),
      weightsWithIntercept(weightsWithIntercept.size - 1))

    //Clear the threshold
    model.clearThreshold()



    sc.stop()

    val result = new LinearRegressionResult


    result.appendResultText("Predict(x=10): " + model.predict(Vectors.dense(10)))
    result.appendResultText("Predict(x=100): " + model.predict(Vectors.dense(100)))


    for (i <- 0 to weightsWithIntercept.toArray.size-1) {
      val slice: Array[Double] = weightsWithIntercept.toArray.slice(i, i+1)
      if (!slice.isEmpty) {
        result.appendResultText("Weight: " + slice(0))
      }
    }

    return result
  }

}

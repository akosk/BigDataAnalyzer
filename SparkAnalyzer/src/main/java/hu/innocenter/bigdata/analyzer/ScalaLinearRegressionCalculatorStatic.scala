package hu.innocenter.bigdata.analyzer

import java.sql.{DriverManager, ResultSet}
import java.util

import hu.innocenter.bigdata.analyzer.Calculator.ParsePoint
import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.feature.{StandardScalerModel, StandardScaler}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.optimization.{Updater, SquaredL2Updater}
import org.apache.spark.mllib.regression.{LassoWithSGD, LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.mllib.util.LinearDataGenerator
import org.apache.spark.rdd.{RDD, JdbcRDD}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by akosk on 2016.02.05..
  */
class ScalaLinearRegressionCalculatorStatic extends Calculator {
  override def calculate(dataSource: String, sqlQuery: String, params: util.HashMap[String, AnyRef]): LinearRegressionResult = {

    Logger.getRootLogger.setLevel(Level.WARN)


    val conf = new SparkConf().setAppName(s"LinearRegression").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlcontext = new SQLContext(sc)


    val p1: LabeledPoint = new LabeledPoint(2, Vectors.dense(1))
    val p2: LabeledPoint = new LabeledPoint(3, Vectors.dense(2))
    val p3: LabeledPoint = new LabeledPoint(4, Vectors.dense(3))
    val p4: LabeledPoint = new LabeledPoint(5, Vectors.dense(4))
    val p5: LabeledPoint = new LabeledPoint(6, Vectors.dense(5))

    val data = Array(p1, p2, p3, p4, p5)
    val labeledPointsRDD: RDD[LabeledPoint] = sc.parallelize(data, 1)


    val scaler = new StandardScaler(withMean = true, withStd = true).fit(labeledPointsRDD.map(x => x.features))

    val scaledData = labeledPointsRDD.map{ data =>
      LabeledPoint(
        data.label,
        scaler.transform(Vectors.dense(data.features.toArray)))
    }


    //        scaler.transform(Vectors.dense(data.label)).toArray.apply(0),

//    val scaledData=labeledPointsRDD;


//    val lasso=new LassoWithSGD().setIntercept(true)
//    lasso.optimizer.setNumIterations(10000).setStepSize(1.0/(2.0*4.0*4.0/5.0))
//    val model=lasso.run(scaledData)



    val linReg = new LinearRegressionWithSGD().setIntercept(true)
//        linReg.optimizer.setNumIterations(100).setStepSize(1.0/(2.0*4.0*4.0/5.0))



    val model = linReg.run(scaledData)

//    scaledData.collect().foreach(println)
    sc.stop()



    val result = new LinearRegressionResult

    result.appendResultText("Predict(x=10): " + model.predict(Vectors.dense(10)))
    result.appendResultText("Predict(x=100): " + model.predict(Vectors.dense(100)))
    result.appendResultText("Intercept: " + model.intercept)
    result.appendResultText("Regression weights: " + model.weights)

    return result
  }

}

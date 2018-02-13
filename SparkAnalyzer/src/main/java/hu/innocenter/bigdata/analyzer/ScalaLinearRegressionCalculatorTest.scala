package hu.innocenter.bigdata.analyzer

import java.util

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.feature.StandardScaler
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.{LinearRegressionWithSGD, LabeledPoint, LassoWithSGD}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by akosk on 2016.02.05..
  */
class ScalaLinearRegressionCalculatorTest extends Calculator {
  override def calculate(dataSource: String, sqlQuery: String, params: util.HashMap[String, AnyRef]): LinearRegressionResult = {

    Logger.getRootLogger.setLevel(Level.WARN)


    val conf = new SparkConf().setAppName(s"LinearRegression").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlcontext = new SQLContext(sc)


    val data: ArrayBuffer[LabeledPoint] = ArrayBuffer()
    val r = scala.util.Random

    var a = 0;
    // for loop execution with a range
    for( a <- 1 to 1000){
            val p: LabeledPoint = new LabeledPoint(a, Vectors.dense(a + (r.nextDouble() - 0.5) * 3))
            data += p
    }



    val labeledPointsRDD: RDD[LabeledPoint] = sc.parallelize(data, 1)


        val scaler = new StandardScaler(withMean = false, withStd = false).fit(labeledPointsRDD.map(x => x.features))

        val scaledData = labeledPointsRDD.map { data =>
          LabeledPoint(
            data.label,
            scaler.transform(Vectors.dense(data.features.toArray)))
        }

    val linReg = new LinearRegressionWithSGD().setIntercept(true)
    linReg.optimizer.setNumIterations(200).setStepSize(0.0001)
    val model = linReg.run(scaledData)

    sc.stop()


    val result = new LinearRegressionResult

    result.appendResultText("Predict(x=10): " + model.predict(Vectors.dense(10)))
    result.appendResultText("Predict(x=100): " + model.predict(Vectors.dense(100)))
    result.appendResultText("Intercept: " + model.intercept)
    result.appendResultText("Regression weights: " + model.weights)

    return result
  }

}

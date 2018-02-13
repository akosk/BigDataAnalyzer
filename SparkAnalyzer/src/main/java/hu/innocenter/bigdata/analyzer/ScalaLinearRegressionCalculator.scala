package hu.innocenter.bigdata.analyzer

import java.sql.{ResultSet, DriverManager}
import java.util

import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.regression.LinearRegression
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.optimization.{L1Updater, SquaredL2Updater, SimpleUpdater}
import org.apache.spark.mllib.regression.{LabeledPoint, LinearRegressionWithSGD}
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.{RDD, JdbcRDD}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by akosk on 2016.02.05..
  */
class ScalaLinearRegressionCalculator extends Calculator {
  override def calculate(dataSource: String, sqlQuery: String, params: util.HashMap[String, AnyRef]): LinearRegressionResult = {

    Logger.getRootLogger.setLevel(Level.WARN)

    val url: String = "jdbc:mysql://localhost:3306/bigdata"
    val username: String = "bigdata"
    val password: String = "bigdata"

    val conf = new SparkConf().setAppName(s"LinearRegression").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlcontext = new SQLContext(sc)


    val labeledPoints = new JdbcRDD(sc, () =>
      DriverManager.getConnection(url, username, password),
      sqlQuery + " AND ?=? ", 1, 1, 1,
      (x: ResultSet) =>
        new LabeledPoint(x.getDouble(1), Vectors.dense(x.getDouble(2)))
    ).cache()

    val updater = new SquaredL2Updater()

    val algorithm = new LinearRegressionWithSGD()

    algorithm.setIntercept(true).optimizer
      .setNumIterations(100)
      .setStepSize(0.01)
      .setUpdater(updater)
      .setRegParam(0.01)

    val model = algorithm.run(labeledPoints)

    sc.stop()

    val predict=model.predict(Vectors.dense(1.0))

    val result = new LinearRegressionResult
    result.appendResultText("Predict: " + predict)
    result.appendResultText("Intercept: " + model.intercept)
    result.appendResultText("Regression weights: " + model.weights)

    return result
  }

  def loadRDDFromMySql(sqlcontext: SQLContext, url: String, username: String, password: String): Unit = {
    val dataframe_mysql = sqlcontext.read.format("jdbc")
      .option("url", url)
      .option("driver", "com.mysql.jdbc.Driver")
      .option("dbtable", "basic_lreg")
      .option("user", username)
      .option("password", password)
      .load()

    dataframe_mysql.show()
  }
}

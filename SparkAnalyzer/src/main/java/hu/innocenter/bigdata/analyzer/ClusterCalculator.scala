package hu.innocenter.bigdata.analyzer

import java.sql.{ResultSet, DriverManager}
import java.util
import java.util.regex.{Matcher, Pattern}

import org.apache.log4j.{Level, Logger}
import org.apache.spark.mllib.classification.{SVMWithSGD, SVMModel}
import org.apache.spark.mllib.feature.{PCAModel, StandardScaler, PCA}
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.{LinearRegressionWithSGD, LabeledPoint}
import org.apache.spark.rdd.{JdbcRDD, RDD}
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors

/**
  * Created by akosk on 2016.02.05..
  */
class ClusterCalculator extends Calculator {
  override def calculate(dataSource: String, sqlQuery: String, params: util.HashMap[String, AnyRef]): ClusterResult = {

    Logger.getRootLogger.setLevel(Level.WARN)
    var fieldnames: Array[String] = params.get("fieldnames").asInstanceOf[Array[String]]

    val conf = new SparkConf().setAppName(s"K-means clustering").setMaster("local")
    val sc = new SparkContext(conf)
    val sqlcontext = new SQLContext(sc)

    val myRDD = new JdbcRDD(sc, () =>
      DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata", "root", "start"),
      sqlQuery + " AND 0 >= ? AND 2 <= ?",
      0, 2, 1,
      (r: ResultSet) => {

        val columnCount = r.getMetaData.getColumnCount

        val x: Array[Double] = new Array[Double](columnCount-1)

        for (i <- 2 to columnCount) {
          x(i - 2) = r.getDouble(i)
        }

        Vectors.dense(x)
      }
    )

    val labeledRDD = new JdbcRDD(sc, () =>
      DriverManager.getConnection("jdbc:mysql://localhost:3306/bigdata", "root", "start"),
      sqlQuery + " AND 0 >= ? AND 2 <= ?",
      0, 2, 1,
      (r: ResultSet) => {

        val columnCount = r.getMetaData.getColumnCount

        val x: Array[String] = new Array[String](columnCount)

        for (i <- 1 to columnCount) {
          x(i - 1) = r.getString(i)
        }

        x
      }
    )

    val numClusters = params.get("clusters").asInstanceOf[String].toInt
    val numIterations = 20
    val clusters = KMeans.train(myRDD, numClusters, numIterations)
    val WSSSE = clusters.computeCost(myRDD)


    val result = new ClusterResult

    result.appendResultText("<h2>Kmeans clustering</h2>");

    result.appendResultText("<table  class='table table-hover table-bordered table-condensed'>")

    for (i <- 0 to clusters.clusterCenters.size - 1) {
      result.appendResultText(s"<tr><th>Cluster ${i}</th><td></td></tr>")

      result.appendResultText(s"<tr>")
      for (c <- 0 to clusters.clusterCenters(i).size - 1) {
        result.appendResultText(s"<td>${fieldnames(c+1)}</td><td>${clusters.clusterCenters(i)(c)}</td></tr>")
      }
      result.appendResultText(s"</tr>")
    }

    result.appendResultText("</table>");


    result.appendResultText("<table class='table table-hover table-bordered table-condensed'>")
    result.appendResultText(s"<tr><td>WSSSE (Sum of Squared Errors)</td><td>${WSSSE}</td></tr>")
    result.appendResultText("</table>")

    val data: Array[Vector] = myRDD.collect()
    val data2: Array[Array[String]] = labeledRDD.collect()

    result.appendResultText("<table class='table table-hover table-bordered table-condensed'>")
//    result.appendResultText(s"<tr><th>Coordinates</th><th>Cluster</th></tr>")
    for (row <- 0 to data.size - 1) {

      var s=""
      for (c <- 0 to data2(row).size-1) {
        s=s+s"<td>${data2(row)(c)}</td>"
      }
      result.appendResultText(s"<tr>${s}<td>${clusters.predict(data(row))}</td></tr>")
    }
    result.appendResultText("</table>")
    return result
  }


}

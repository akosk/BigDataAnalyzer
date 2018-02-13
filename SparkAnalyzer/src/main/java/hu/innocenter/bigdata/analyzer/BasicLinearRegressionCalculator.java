package hu.innocenter.bigdata.analyzer;


import org.apache.spark.Partition;
import org.apache.spark.SparkConf;
import org.apache.spark.TaskContext;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.evaluation.RegressionMetrics;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;
import scala.collection.Iterator;

import java.util.HashMap;


/**
 * Created by Ákos on 2015.09.22..
 */
public class BasicLinearRegressionCalculator extends Calculator {


    static class DoubleToObject implements Function<Tuple2<Double, Double>, Tuple2<Object, Object>> {

        @Override
        public Tuple2<Object, Object> call(Tuple2<Double, Double> point) {

            return new Tuple2<Object, Object>(point._1, point._2);
        }
    }

    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {


        JavaRDD<Vector> points = null;
        JavaRDD<LabeledPoint> labeledPoints = null;

        LinearRegressionResult result = new LinearRegressionResult();

        sparkConf = new SparkConf().setAppName("JavaLR").setMaster("local");
        sc = new JavaSparkContext(sparkConf);


        try {
            labeledPoints = readLabeledPointsFromDB(dataSource, sqlQuery);

            double stepSize = 0.01;
            int iterations = 2000;
            RDD<LabeledPoint> labeledPointRDD = labeledPoints.rdd();

            LinearRegressionModel model = (LinearRegressionModel) new LinearRegressionWithSGD(stepSize, iterations, 1)
                    .setIntercept(true)
                    .run(labeledPointRDD, Vectors.dense(5.0));


            Object labeledPoints1 = labeledPointRDD.collect();

            double predict = model.predict(Vectors.dense(1.0));

            result.appendResultText("Predict: " + predict);
            result.appendResultText("Intercept: " + model.intercept());
            result.appendResultText("Regression weights: " + model.weights());




        } catch (Exception e) {
            result.setResultText(e.getMessage());
        } finally {
            sc.stop();
        }

        return result;
    }
}

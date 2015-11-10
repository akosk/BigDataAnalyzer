package hu.innocenter.bigdata.analyzer;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;
import org.apache.spark.mllib.evaluation.RegressionMetrics;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.regression.LabeledPoint;

import java.util.HashMap;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.rdd.RDD;
import scala.Tuple2;


/**
 * Created by Ákos on 2015.09.22..
 */
public class LinearRegressionCalculator2 extends Calculator {


    static class DoubleToObject implements Function<Tuple2<Double, Double>, Tuple2<Object, Object>> {

        @Override
        public Tuple2<Object, Object> call (Tuple2<Double, Double> point) {

            return new Tuple2 <Object, Object> (point._1, point._2);
        }
    }
    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {
        JavaRDD<Vector> points = null;
        JavaRDD<LabeledPoint> labeledPoints = null;
        
        LogisticRegressionResult result = new LogisticRegressionResult();
        result.setResultText("");
        

        sparkConf = new SparkConf().setAppName("JavaLR").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        StandardScaler scaler = new StandardScaler(true, true);
        out.println("%%%%%%%% SCALER: " + scaler);


        try {
            labeledPoints = readLabeledPointsFromDB(dataSource, sqlQuery);
            StandardScalerModel scalerModel = scaler.fit(labeledPoints.map(new ParsePoint()).rdd());
            out.println("%%%%%%%% SCALER MODEL: " + scalerModel.withMean() + "--" + scalerModel.withStd());
            // scaler.


            points = labeledPoints.map(new ParsePoint()).cache();

            points = scalerModel.transform(points);

/*
            if (pcaNum > 0) { // Run Principal Component Analysis
                // Create a RowMatrix from JavaRDD<Vector>.
                RowMatrix mat = new RowMatrix(points.rdd());
                //out.println("***Rows: " + mat.numRows());
                //out.println("***Cols: " + mat.numCols());
                // Compute the top pcaNum principal components.
                Matrix pc = mat.computePrincipalComponents(pcaNum);

                //out.println("***Rows: " + pc.numRows());
                //out.println("***Cols: " + pc.numCols());

                out.println("Principal components:");
                out.println(pc.toString());

                labeledPoints = transformPointsToPCs(mat, pc, labeledPoints);

            }
*/
            // Run Logistic regression
            double stepSize = 2;//Double.parseDouble(args[1]);
            int iterations = 200;//Integer.parseInt(args[2]);
            LinearRegressionModel model = LinearRegressionWithSGD.train(labeledPoints.rdd(), iterations, stepSize);
            out.println("Regression weights: " + model.weights());

            result.setResultText("Regression weights: " + model.weights());
            
            // Calculate regression metrics
            
            JavaRDD<java.lang.Double> observations = labeledPoints.map(new ParseLabel()).cache();
            
            
            JavaRDD<java.lang.Double> predictions = model.predict(points);
            JavaPairRDD<Double, Double> zipped = observations.zip(predictions);
            
            
            RegressionMetrics metrics = new RegressionMetrics(zipped.map(new DoubleToObject()).cache().rdd());
            
            
            out.println("Regression explained variance: " + metrics.explainedVariance());
            result.setResultText("Regression explained variance: " + metrics.explainedVariance());
            out.println("Regression mean absolute error: " + metrics.meanAbsoluteError());
            result.setResultText("Regression mean absolute error: " + metrics.meanAbsoluteError());
            out.println("Regression mean squared error: " + metrics.meanSquaredError());
            result.setResultText("Regression mean squared error: " + metrics.meanSquaredError());
            out.println("Regression root main squared error: " + metrics.rootMeanSquaredError());
            result.setResultText("Regression root main squared error: " + metrics.rootMeanSquaredError());

            sc.stop();

        } catch (Exception e) {
            out.println("****** " + e.getMessage());
            result.setResultText(e.getMessage());

        }

        return result;
    }
}

package hu.innocenter.bigdata.analyzer;


import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.regression.LabeledPoint;

import java.util.HashMap;

import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.mllib.regression.LinearRegressionWithSGD;

/**
 * Created by Ákos on 2015.09.22..
 */
public class LinearRegressionCalculator extends Calculator {

    Logger log=Logger.getLogger(LinearRegressionCalculator.class);

    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {

        JavaRDD<Vector> points = null;
        JavaRDD<LabeledPoint> labeledPoints = null;
        
        LinearRegressionResult result = new LinearRegressionResult();
        result.setResultText("");
        

        sparkConf = new SparkConf().setAppName("JavaLR").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        StandardScaler scaler = new StandardScaler(true, true);
        out.println("%%%%%%%% SCALER: " + scaler);
        log.error("%%%%%%%% SCALER: " + scaler);


        try {
            labeledPoints = readLabeledPointsFromDB(dataSource, sqlQuery);
            StandardScalerModel scalerModel = scaler.fit(labeledPoints.map(new ParsePoint()).rdd());
            out.println("%%%%%%%% SCALER MODEL: " + scalerModel.withMean() + "--" + scalerModel.withStd());
            log.error("%%%%%%%% SCALER MODEL: " + scalerModel.withMean() + "--" + scalerModel.withStd());
            // scaler.


            points = labeledPoints.map(new ParsePoint()).cache();
            log.error("P1 "+points.count());
            points = scalerModel.transform(points);
            log.error("P2 "+points.count());
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
            // Run Linear regression
            double stepSize = 2;//Double.parseDouble(args[1]);
            int iterations = 200;//Integer.parseInt(args[2]);
            LinearRegressionModel model = LinearRegressionWithSGD.train(labeledPoints.rdd(), iterations, stepSize);
            out.println("Regression weights: " + model.weights());
            log.error("Regression weights: " + model.weights());


            result.setResultText("Regression weights: " + model.weights());
            
            out.println("System trained");
            log.error("System trained");


            sc.stop();

        } catch (Exception e) {
            out.println("****** " + e.getMessage());
            result.setResultText(e.getMessage());

        }

        return result;
    }
}

package hu.innocenter.bigdata.analyzer;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.feature.StandardScaler;
import org.apache.spark.mllib.feature.StandardScalerModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.regression.LabeledPoint;

import java.util.HashMap;

import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;

/**
 * Created by Ákos on 2015.09.22..
 */
public class KMeansClusterCalculator extends Calculator {

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
            // Run clustering
           int k = 2;
            int iterations = 3;
            int runs = 10;

            KMeansModel model;
            model = KMeans.train(points.rdd(), k, iterations, runs, KMeans.K_MEANS_PARALLEL());
        
            StringBuffer resultText = new StringBuffer();
            
            out.println("Cluster centers:");
            resultText.append("Cluster centers:\n");
            for (Vector center : model.clusterCenters()) {
                out.println(" " + center);
                resultText.append(" " + center + "\n");
            }
            
            double cost = model.computeCost(points.rdd());
            out.println("Cost: " + cost);
            resultText.append("Cost: " + cost + "\n");

            out.println("System trained");    

            result.setResultText(resultText.toString());
            sc.stop();

        } catch (Exception e) {
            out.println("****** " + e.getMessage());
            result.setResultText(e.getMessage());

        }

        return result;
    }
}

package hu.innocenter.bigdata.analyzer;


import java.util.Comparator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.HashMap;

import org.apache.spark.api.java.JavaDoubleRDD;

/**
 * Created by Ákos on 2015.09.22..
 */
public class MaximumCalculator extends Calculator {

    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {
        JavaRDD<Double> points = null;
        JavaDoubleRDD dblPoints = null;
        
        MaximumResult result = new MaximumResult();
        result.setResultText("");
        

        sparkConf = new SparkConf().setAppName("JavaMean").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        try {
            points = readDoublesFromDB(dataSource, sqlQuery);
            
            dblPoints = points.mapToDouble(d -> d);
            
            double max = dblPoints.max(new DoubleComparator());
            
            out.println("Maximum value: " + max);

            result.setResultText("Maximum value:" + max);
            
            sc.stop();

        } catch (Exception e) {
            out.println("****** " + e.getMessage());
            result.setResultText(e.getMessage());

        }

        return result;
    }
}

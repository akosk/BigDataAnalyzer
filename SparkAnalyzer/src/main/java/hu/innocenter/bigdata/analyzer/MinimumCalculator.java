package hu.innocenter.bigdata.analyzer;


import java.io.Serializable;
import java.util.Comparator;

import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.HashMap;

import org.apache.spark.api.java.JavaDoubleRDD;

/**
 * Created by Ákos on 2015.09.22..
 */
public class MinimumCalculator extends Calculator {

    Logger log = Logger.getLogger(MinimumCalculator.class);

    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {
        JavaRDD<Double> points = null;
        JavaDoubleRDD dblPoints = null;
        
        MinimumResult result = new MinimumResult();
        result.setResultText("");
        

        sparkConf = new SparkConf().setAppName("JavaMean").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        try {
            points = readDoublesFromDB(dataSource, sqlQuery);
            
            dblPoints = points.mapToDouble(d -> d);
            
            double min = dblPoints.min(new DoubleComparator());
            
            out.println("Minimum value: " + min);

            result.setResultText("Minimum value:" + min);
            


        } catch (Exception e) {
            log.error(e.getMessage());
            out.println("****** " + e.getMessage());
            result.setResultText(e.getMessage());

        } finally {
            sc.stop();
        }

        return result;
    }
}

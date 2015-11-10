package hu.innocenter.bigdata.analyzer;


import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.HashMap;

import org.apache.spark.api.java.JavaDoubleRDD;

/**
 * Created by Ákos on 2015.09.22..
 */
public class VarianceCalculator extends Calculator {

    private Logger log = Logger.getLogger(VarianceCalculator.class);

    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {
        JavaRDD<Double> points = null;
        JavaDoubleRDD dblPoints = null;

        VarianceResult result = new VarianceResult();
        result.setResultText("");


        sparkConf = new SparkConf().setAppName("JavaMean").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        try {
            points = readDoublesFromDB(dataSource, sqlQuery);

            dblPoints = points.mapToDouble(d -> d);

            double variance = dblPoints.variance();

            out.println("Variance value: " + variance);

            result.setResultText("Variance value:" + variance);


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

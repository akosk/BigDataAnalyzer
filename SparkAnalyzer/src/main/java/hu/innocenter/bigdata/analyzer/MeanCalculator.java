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
public class MeanCalculator extends Calculator {

    private Logger log = Logger.getLogger(MeanCalculator.class);

    @Override
    public Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params) {
        JavaRDD<Double> points = null;
        JavaDoubleRDD dblPoints = null;

        MeanResult result = new MeanResult();
        result.setResultText("");


        sparkConf = new SparkConf().setAppName("JavaMean").setMaster("local");
        sc = new JavaSparkContext(sparkConf);

        try {
            points = readDoublesFromDB(dataSource, sqlQuery);

            dblPoints = points.mapToDouble(d -> d);

            double mean = dblPoints.mean();

            out.println("Mean value: " + mean);

            result.setResultText("Mean value:" + mean);


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

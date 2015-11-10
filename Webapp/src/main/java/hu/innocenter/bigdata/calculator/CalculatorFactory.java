package hu.innocenter.bigdata.calculator;

import hu.innocenter.bigdata.analyzer.*;
import hu.innocenter.bigdata.model.CalculationConfiguration;

/**
 * Created by Ákos Kiszely on 2015.11.30..
 * akos.kiszely@gmail.com
 */
public class CalculatorFactory {


    public static Calculator createCalculatorById(CalculationConfiguration config) {
        Calculator calculator = null;
        switch (config.getCalculation_id()) {
            case "min":
                calculator = new MinimumCalculator();
                break;
            case "max":
                calculator = new MaximumCalculator();
                break;
            case "mean":
                calculator = new MeanCalculator();
                break;
            case "variance":
                calculator = new VarianceCalculator();
                break;
            case "linear-regression":
                calculator = new LinearRegressionCalculator();
                break;
        }

        return calculator;
    }
}

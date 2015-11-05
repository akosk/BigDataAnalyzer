package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.analyzer.RegressionCalculator;
import hu.innocenter.bigdata.analyzer.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;


/**
 * Created by Ákos Kiszely on 2015.11.02..
 * akos.kiszely@gmail.com
 */

@Controller
public class SiteController {

    @RequestMapping("/index")
    public String sayHello(Model model) {
        model.addAttribute("actionId", "Hello World");
        return "welcome";
    }

    @RequestMapping(value = "/calculation-config")
    public String calculationConfig(Model model) {
        return "calculation-config";
    }

    @RequestMapping(value = "/spark-test")
    public String sparkTest(Model model) {

        RegressionCalculator regressionCalculator = new RegressionCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();

        Result s = regressionCalculator.calculate("java:comp/env/jdbc/bigdata", "SELECT 1.0 valami, id, user_id, year, month, amount FROM payment WHERE 1=1 ", params);
        model.addAttribute("result", s);

        return "spark-test";
    }

    @RequestMapping("/data-manager")
    public String dataUploadKozetpalast(Model model) {
        return "data-manager";
    }

}

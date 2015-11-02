package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.analyzer.RegressionCalculator;
import hu.innocenter.bigdata.analyzer.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


/**
 * Created by Ákos Kiszely on 2015.11.02..
 * akos.kiszely@gmail.com
 */

@Controller
public class SiteController {

    @RequestMapping(value = "/index")
    public String sayHello(Model model) {
        model.addAttribute("greeting", "Hello World");
        return "welcome.jsp";
    }

    @RequestMapping(value = "/calculation-config")
    public String calculationConfig(Model model) {
        return "calculation-config.jsp";
    }

    @RequestMapping(value = "/spark-test")
    public String sparkTest(Model model) {

        RegressionCalculator regressionCalculator = new RegressionCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();

        Result s = regressionCalculator.calculate("java:comp/env/jdbc/bigdata", "SELECT id, user_id, year, month, amount FROM payment WHERE 1=1 ", params);
        model.addAttribute("result", s);

        return "spark-test.jsp";
    }
}

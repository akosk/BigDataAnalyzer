package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.ApplicationConfig;
import hu.innocenter.bigdata.analyzer.KMeansClusterCalculator;
import hu.innocenter.bigdata.analyzer.LinearRegressionCalculator;
import hu.innocenter.bigdata.analyzer.LogisticRegressionCalculator;
import hu.innocenter.bigdata.analyzer.Result;
import hu.innocenter.bigdata.model.CalculationConfiguration;
import hu.innocenter.bigdata.service.CementesKozetModellService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;
import java.util.HashMap;


/**
 * Created by Ákos Kiszely on 2015.11.02..
 * akos.kiszely@gmail.com
 */

@Controller
public class SiteController {

    Logger log = Logger.getLogger(SiteController.class);



    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return ApplicationConfig.mode == ApplicationConfig.MODE.LASER ? "welcome-laser" : "welcome-cement";
    }


    @RequestMapping(value = "/calculation-config")
    public String calculationConfig(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "calculation-config";
    }

    @RequestMapping(value = "/spark-test-logistic-regression")
    public String sparkLogisticTest(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        LogisticRegressionCalculator regressionCalculator = new LogisticRegressionCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();

        Result s = regressionCalculator.calculate("java:comp/env/jdbc/bigdata", "SELECT 1.0 valami, id, user_id, year, month, amount FROM payment WHERE 1=1 ", params);
        model.addAttribute("result", s.getResultText());

        return "spark-test";
    }

    @RequestMapping(value = "/spark-test-linear-regression")
    public String sparkLinearTest(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        LinearRegressionCalculator regressionCalculator = new LinearRegressionCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();

        Result s = regressionCalculator.calculate("java:comp/env/jdbc/bigdata", "SELECT 1.0 valami,id, user_id, year, month, amount FROM payment WHERE 1=1 ", params);
        model.addAttribute("result", s.getResultText());

        return "spark-test";
    }

    @RequestMapping(value = "/spark-test-linear-regression2")
    public String sparkLinearTest2(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        LinearRegressionCalculator regressionCalculator = new LinearRegressionCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();

//        String q = "SELECT k.belso_atmero, k.homok_frakcio_1, k.homok_frakcio_2, k.homok_frakcio_3, k.homok_frakcio_4, k.anyag_frakcio_1, f.Kw, f.TC FROM kozetmodell k \n" +
//                "INNER JOIN fluidum f ON f.kozetmodell_kod=k.kozetmodell_kod WHERE 1=1 ";

        String q = "SELECT IFNULL(f.Kw,f.TC), k.belso_atmero, k.homok_frakcio_1, k.homok_frakcio_2, k.homok_frakcio_3, k.homok_frakcio_4, k.anyag_frakcio_1  FROM kozetmodell k \n" +
                "INNER JOIN fluidum f ON f.kozetmodell_kod=k.kozetmodell_kod WHERE 1=1 ";
//        String q = "SELECT IFNULL(f.Kw,f.TC), k.belso_atmero, k.homok_frakcio_1  FROM kozetmodell k \n" +
//                "INNER JOIN fluidum f ON f.kozetmodell_kod=k.kozetmodell_kod WHERE 1=1 ";
//        String q = "SELECT a,b,c  FROM test WHERE 1=1 ";
        Result s = regressionCalculator.calculate("java:comp/env/jdbc/bigdata", q, params);
        model.addAttribute("result", s.getResultText());

        return "spark-test";
    }

    @RequestMapping(value = "/spark-test-cluster-regression")
    public String sparkClusterTest(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        KMeansClusterCalculator kMeansCalculator = new KMeansClusterCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();

        Result s = kMeansCalculator.calculate("java:comp/env/jdbc/bigdata", "SELECT 1.0 valami,id, user_id, year, month, amount FROM payment WHERE 1=1 ", params);
        model.addAttribute("result", s.getResultText());

        return "spark-test";
    }

    @RequestMapping(value = "/tasks")
    public String tasks(Model model) {

        model.addAttribute("layout", ApplicationConfig.layout);
        return "tasks";
    }

    @RequestMapping("/data-manager-laser")
    public String dataManagerLaser(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-laser";
    }

    @RequestMapping("/data-manager-cement")
    public String dataManagerCement(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-cement";
    }

    @RequestMapping("/data-manager-fluidum")
    public String dataManagerFluidum(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-fluidum";
    }

    @RequestMapping("/data-manager-meresi-korulmeny")
    public String dataManagerMeresiKorulmeny(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-meresi-korulmeny";
    }


    @RequestMapping("/data-manager-meresi-eredmeny-lezer")
    public String dataManagerMeresiEredmenyLezer(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-meresi-eredmeny-lezer";
    }
    @RequestMapping("/data-manager-meresi-eredmeny-cement")
    public String dataManagerMeresiEredmenyCement(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-meresi-eredmeny-cement";
    }

    @RequestMapping("/data-manager-job")
    public String dataManagerJob(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-job";
    }


}

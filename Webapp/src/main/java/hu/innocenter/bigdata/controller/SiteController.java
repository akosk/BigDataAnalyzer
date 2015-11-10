package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.ApplicationConfig;
import hu.innocenter.bigdata.analyzer.*;
import hu.innocenter.bigdata.model.CalculationConfiguration;
import hu.innocenter.bigdata.service.CementesKozetModellService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "/spark-test-min")
    public String sparkMinTest(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        MinimumCalculator minCalculator = new MinimumCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();


        String q = "SELECT id FROM payment WHERE 1=1 ";
        Result s = minCalculator.calculate("java:comp/env/jdbc/bigdata", q, params);
        model.addAttribute("result", s.getResultText());

        return "spark-test";
    }

    @RequestMapping(value = "/spark-test-max")
    public String sparkMaxTest(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        Calculator calculator = new MaximumCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();


        String q = "SELECT id FROM payment WHERE 1=1 ";
        Result s = calculator.calculate("java:comp/env/jdbc/bigdata", q, params);
        model.addAttribute("result", s.getResultText());

        return "spark-test";
    }

    @RequestMapping(value = "/spark-test-mean")
    public String sparkMeanTest(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        MeanCalculator meanCalculator = new MeanCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();


        String q = "SELECT id FROM payment WHERE 1=1 ";
        Result s = meanCalculator.calculate("java:comp/env/jdbc/bigdata", q, params);
        model.addAttribute("result", s.getResultText());

        return "spark-test";
    }

    @RequestMapping(value = "/spark-test-variance")
    public String sparkVarianceTest(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);

        Calculator calculator = new VarianceCalculator();
        HashMap<String, Object> params = new HashMap<String, Object>();


        String q = "SELECT id FROM payment WHERE 1=1 ";
        Result s = calculator.calculate("java:comp/env/jdbc/bigdata", q, params);
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

    @RequestMapping("/data-manager-user")
    public String dataManagerUser(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        return "data-manager-user";
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

    // Login form
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("layout", "layout-login");
        String title = ApplicationConfig.mode== ApplicationConfig.MODE.CEMENT?
                "Kőzet, cementpalást, acélcső kőzetmodelleken végzett\n" +
                        " laboratóriumi kísérleti eredmények adatbázisainak feldolgozása"
                :
                "Kőzet, függőleges furatú, vízszintes lézeres befúrású kőzetmodelleken végzett\n" +
                        " laboratóriumi kísérleti eredmények adatbázisainak feldolgozása";
        model.addAttribute("title", title);
        model.addAttribute("navbarCss", ApplicationConfig.mode== ApplicationConfig.MODE.CEMENT ?"css/navbar.css":"css/navbar2.css");
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("layout", ApplicationConfig.layout);
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

}

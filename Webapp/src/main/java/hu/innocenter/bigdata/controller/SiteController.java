package hu.innocenter.bigdata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Ákos Kiszely on 2015.11.02..
 * akos.kiszely@gmail.com
 */

@Controller
public class SiteController {

    @RequestMapping(value = "/welcome")
    public String sayHello(Model model) {
        model.addAttribute("greeting", "Hello World");
        return "welcome.jsp";
    }
}

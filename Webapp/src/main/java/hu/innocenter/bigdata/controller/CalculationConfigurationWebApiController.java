package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.service.CalculationConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */


@RestController
public class CalculationConfigurationWebApiController {

    @Autowired
    private CalculationConfigurationService calculationConfigurationService;


    @RequestMapping(value = "/webapi-calculation-configurations", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createCalculationConfiguration() {

        Date now = new Date();
//        calculationConfiguration.setCreated(now);
//        calculationConfiguration.setUpdated(now);
//        calculationConfigurationService.saveCalculationConfiguration(calculationConfiguration);
    }
}

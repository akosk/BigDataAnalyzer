package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.ApplicationConfig;
import hu.innocenter.bigdata.model.CalculationConfiguration;
import hu.innocenter.bigdata.model.ColumnCondition;
import hu.innocenter.bigdata.model.ColumnConfig;
import hu.innocenter.bigdata.model.SelectedTable;
import hu.innocenter.bigdata.service.CalculationConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

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
    public void createCalculationConfiguration(@RequestBody CalculationConfiguration calculationConfiguration) {

        Date now = new Date();
        calculationConfiguration.setCreated(now);
        calculationConfiguration.setUpdated(now);

        setForeignKeys(calculationConfiguration);

        calculationConfigurationService.saveCalculationConfiguration(calculationConfiguration);
    }

    private void setForeignKeys(CalculationConfiguration calculationConfiguration) {
        for (SelectedTable item : calculationConfiguration.getSelectedTables()) {
            item.setCalculationConfiguration(calculationConfiguration);
        }

        for (ColumnConfig item : calculationConfiguration.getColumnConfigs()) {
            item.setCalculationConfiguration(calculationConfiguration);
        }

        for (ColumnCondition item : calculationConfiguration.getColumnConditions()) {
            item.setCalculationConfiguration(calculationConfiguration);
            for (ColumnCondition child : item.getColumnConditions()) {
                child.setColumnCondition(item);
            }
        }
    }


    @RequestMapping(value = "/webapi-calculation-configurations", method = RequestMethod.GET, produces = "application/json")
    public List<CalculationConfiguration> tableTest(Model model) {


        List<CalculationConfiguration> allCalculationConfigurations = calculationConfigurationService.findAllCalculationConfigurations();
        return allCalculationConfigurations;

    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-calculation-configurations/{id}"}, method = RequestMethod.DELETE)
    public void deleteKozetModell(@PathVariable Integer id, Model model) {
        CalculationConfiguration calculationConfiguration = calculationConfigurationService.findById(id);
        calculationConfigurationService.deleteCalculationConfiguration(calculationConfiguration);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi-calculation-configurations/{id}"}, method = RequestMethod.PUT)
    public CalculationConfiguration editCalculationConfiguration(@RequestBody CalculationConfiguration calculationConfiguration, Model model, @PathVariable String id) {

        Date now = new Date();
        calculationConfiguration.setUpdated(now);

        calculationConfigurationService.updateCalculationConfiguration(calculationConfiguration);

        return calculationConfiguration;
    }

    @RequestMapping(value = "/webapi-calculation-configurations-list", method = RequestMethod.GET, produces = "application/json")
    public String list(Model model) {


        StringBuilder sb=new StringBuilder();
        List<CalculationConfiguration> allCalculationConfigurations = calculationConfigurationService.findAllCalculationConfigurations();

        for (CalculationConfiguration item : allCalculationConfigurations) {
            if (sb.length()!=0) sb.append(", ");
            sb.append("{\"name\":\""+item.getName()+"\", \"value\":\""+item.getId()+"\"}");

        }
        String res = "[" + sb.toString() + "]";
        return res;

    }

}

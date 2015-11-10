package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.DataSource;
import hu.innocenter.bigdata.model.DataSourcesResponse;
import hu.innocenter.bigdata.model.KozetModell;
import hu.innocenter.bigdata.repository.DataSourceRepository;
import hu.innocenter.bigdata.repository.DataSourceRepositoryLocalMySql;
import hu.innocenter.bigdata.service.KozetModellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created by Ákos Kiszely on 2015.11.03..
 * akos.kiszely@gmail.com
 */

@RestController
public class WebApiController {

    private DataSourceRepository dataSourceRepository = new DataSourceRepositoryLocalMySql();

    @Autowired
    private KozetModellService service;

    @RequestMapping(value = "/webapi/datasources", produces = "application/json")
    public DataSourcesResponse getAllDataSources(Model model) {
        List<DataSource> dataSources = dataSourceRepository.findAllDataSources();
        DataSourcesResponse dataSourcesResponse = new DataSourcesResponse();
        dataSourcesResponse.setDatasources(dataSources);

        return dataSourcesResponse;

    }

    @RequestMapping(value = "/webapi/kozet-modells", method = RequestMethod.GET, produces = "application/json")
    public List<KozetModell> queryKozetModell(Model model) {

        List<KozetModell> kozetModells = service.findAllKozetModells();
        return kozetModells;

    }

    @RequestMapping(value = "/webapi/kozet-modells", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void createKozetModell(@RequestBody KozetModell kozetModell) {

        Date now = new Date();
        kozetModell.setCreated(now);
        kozetModell.setUpdated(now);
        service.saveKozetModell(kozetModell);
    }

    @RequestMapping(value = {"/webapi/kozet-modells/{id}"}, method = RequestMethod.GET, produces = "application/json")
    public KozetModell getKozetModell(@PathVariable Integer id, Model model) {
        KozetModell kozetModell = service.findById(id);
        return kozetModell;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi/kozet-modells/{id}"}, method = RequestMethod.DELETE)
    public void deleteKozetModell(@PathVariable Integer id, Model model) {
        KozetModell kozetModell = service.findById(id);
        service.deleteKozetModell(kozetModell);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = {"/webapi/kozet-modells/{id}"}, method = RequestMethod.PUT)
    public KozetModell editKozetModell(@RequestBody KozetModell kozetModell, Model model, @PathVariable String id) {

        Date now = new Date();
        kozetModell.setUpdated(now);

        service.updateKozetModell(kozetModell);

        return kozetModell;
    }
}

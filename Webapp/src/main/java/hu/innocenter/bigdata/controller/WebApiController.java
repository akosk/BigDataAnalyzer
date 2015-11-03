package hu.innocenter.bigdata.controller;

import hu.innocenter.bigdata.model.DataSource;
import hu.innocenter.bigdata.model.DataSourcesResponse;
import hu.innocenter.bigdata.repository.DataSourceRepository;
import hu.innocenter.bigdata.repository.DataSourceRepositoryLocalMySql;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.03..
 * akos.kiszely@gmail.com
 */

@RestController
public class WebApiController {

    private DataSourceRepository dataSourceRepository = new DataSourceRepositoryLocalMySql();

    @RequestMapping(value = "/webapi/datasources", produces = "application/json")
    public DataSourcesResponse getAllDataSources(Model model) {
        List<DataSource> dataSources = dataSourceRepository.findAllDataSources();
        DataSourcesResponse dataSourcesResponse = new DataSourcesResponse();
        dataSourcesResponse.setDatasources(dataSources);

        return dataSourcesResponse;

    }

}

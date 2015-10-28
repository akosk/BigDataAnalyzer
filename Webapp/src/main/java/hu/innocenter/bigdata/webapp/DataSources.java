package hu.innocenter.bigdata.webapp;

/**
 * Created by Ákos on 2015.10.27..
 */


import hu.innocenter.bigdata.hu.innocenter.bigdata.repository.DataSourceRepository;
import hu.innocenter.bigdata.hu.innocenter.bigdata.repository.DataSourceRepositoryLocalMySql;
import hu.innocenter.bigdata.hu.innocenter.bigdata.repository.DataSourceRepositoryStub;
import hu.innocenter.bigdata.model.DataSource;
import hu.innocenter.bigdata.model.DataSourcesResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("datasources")
public class DataSources {

    private DataSourceRepository dataSourceRepository = new DataSourceRepositoryLocalMySql();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DataSourcesResponse getAllDatasources() {

        List<DataSource> dataSources = dataSourceRepository.findAllDataSources();
        DataSourcesResponse dataSourcesResponse = new DataSourcesResponse();
        dataSourcesResponse.setDatasources(dataSources);

        return dataSourcesResponse;
    }

}

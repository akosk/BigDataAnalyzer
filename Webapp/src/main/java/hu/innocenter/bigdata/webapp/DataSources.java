package hu.innocenter.bigdata.webapp;

/**
 * Created by Ákos on 2015.10.27..
 */


import hu.innocenter.bigdata.hu.innocenter.bigdata.repository.DataSourceRepository;
import hu.innocenter.bigdata.hu.innocenter.bigdata.repository.DataSourceRepositoryStub;
import hu.innocenter.bigdata.model.DataSource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("datasources")
public class DataSources {

    private DataSourceRepository dataSourceRepository = new DataSourceRepositoryStub();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DataSource> getAllDatasources() {

        List<DataSource> dataSources = dataSourceRepository.findAllDataSources();

        return dataSources;
    }

}

package hu.innocenter.bigdata.hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.DataBase;
import hu.innocenter.bigdata.model.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2015.10.27..
 */
public class DataSourceRepositoryStub implements DataSourceRepository {

    @Override
    public List<DataSource> findAllDataSources() {
        List<DataSource> datasources = new ArrayList<DataSource>();

        DataSource dataSource = new DataSource();
        dataSource.setId(1);
        dataSource.setName("Hello");

        List<DataBase> databases=new ArrayList<DataBase>();
        DataBase dataBase = new DataBase();
        dataBase.setSqlName("db neve");
        databases.add(dataBase);
        dataSource.setDatabases(databases);
        datasources.add(dataSource);

        DataSource dataSource2 = new DataSource();
        dataSource2.setId(2);
        dataSource2.setName("Leó");
        datasources.add(dataSource2);

        return datasources;
    }

}

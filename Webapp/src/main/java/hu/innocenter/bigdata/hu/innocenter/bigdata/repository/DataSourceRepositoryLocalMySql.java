package hu.innocenter.bigdata.hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.DataBase;
import hu.innocenter.bigdata.model.DataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2015.10.27..
 */
public class DataSourceRepositoryLocalMySql implements DataSourceRepository {

    @Override
    public List<DataSource> findAllDataSources() {
        List<DataSource> datasources = new ArrayList<DataSource>();

        return datasources;
    }

}

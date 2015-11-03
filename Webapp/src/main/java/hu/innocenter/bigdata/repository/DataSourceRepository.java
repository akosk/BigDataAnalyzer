package hu.innocenter.bigdata.repository;

import hu.innocenter.bigdata.model.DataSource;

import java.util.List;

/**
 * Created by Ákos on 2015.10.27..
 */
public interface DataSourceRepository {
    List<DataSource> findAllDataSources();
}

package hu.innocenter.bigdata.analyzer;

import javax.sql.DataSource;
import java.util.HashMap;

import java.sql.Connection;

/**
 * Created by Ákos Kiszely on 2015.11.02..
 * akos.kiszely@gmail.com
 */
public interface Calculator {
    Result calculate(String dataSource, String sqlQuery, HashMap<String, Object> params);
}

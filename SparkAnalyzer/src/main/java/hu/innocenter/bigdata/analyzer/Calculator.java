package hu.innocenter.bigdata.analyzer;

import scala.collection.immutable.HashMap;

import java.sql.Connection;

/**
 * Created by Ákos Kiszely on 2015.11.02..
 * akos.kiszely@gmail.com
 */
public interface Calculator {
    Result calculate(Connection connection, String sqlQuery, HashMap<String, Object> params);
}

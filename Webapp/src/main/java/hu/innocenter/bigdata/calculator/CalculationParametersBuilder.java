package hu.innocenter.bigdata.calculator;

import hu.innocenter.bigdata.model.CalculationConfiguration;
import hu.innocenter.bigdata.model.ColumnConfig;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by Ákos Kiszely on 2015.11.30..
 * akos.kiszely@gmail.com
 */
public class CalculationParametersBuilder {

    private CalculationConfiguration config;

    public CalculationParametersBuilder(CalculationConfiguration config) {
        this.config = config;
    }

    public String getSqlQuery() {
        String q = "";

        HashMap<String, List<ColumnConfig>> groups = slice();

        StringUtils j = new StringUtils();
        ArrayList<String> columns = new ArrayList<>();
        for (ColumnConfig columnConfig : groups.get("X")) {
            columns.add(columnConfig.getTableName() + "." + columnConfig.getColumnName());
        }

        String fields = j.join(columns, ",");


        q = "SELECT "+fields+" FROM payment WHERE 1=1 ";
        return q;
    }

    private HashMap<String, List<ColumnConfig>> slice() {
        HashMap<String, List<ColumnConfig>> groups = new HashMap<>();

        for (ColumnConfig columnConfig : config.getColumnConfigs()) {
            List<ColumnConfig> configs = groups.get(columnConfig.getType());
            if (configs == null) {
                configs = new ArrayList<>();
                groups.put(columnConfig.getType(), configs);
            }
            configs.add(columnConfig);
        }
        return groups;
    }

    public HashMap<String, Object> getParams() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        return params;
    }
}

package hu.innocenter.bigdata.calculator;

import com.google.common.collect.ImmutableMap;
import hu.innocenter.bigdata.ApplicationConfig;
import hu.innocenter.bigdata.model.CalculationConfiguration;
import hu.innocenter.bigdata.model.ColumnCondition;
import hu.innocenter.bigdata.model.ColumnConfig;
import hu.innocenter.bigdata.model.SelectedTable;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by Ákos Kiszely on 2015.11.30..
 * akos.kiszely@gmail.com
 */
public class CalculationParametersBuilder {

    private CalculationConfiguration config;

    Map<String, String> keyNames = ImmutableMap.of(
            "fluidum", "fluidum_kod",
            "cementes_kozetmodell", "kozetmodell_kod",
            "lezeres_kozetmodell", "kozetmodell_kod",
            "meresi_korulmeny", "korulmeny_kod"
    );

    Map<String, List<String>> groupOrders = ImmutableMap.of(
            "linear-regression", Arrays.asList("x", "y"),
            "min", Arrays.asList("min"),
            "max", Arrays.asList("max"),
            "mean", Arrays.asList("mean"),
            "variance", Arrays.asList("variance")
    );

    Map<String, List<String>> propertyConfig = ImmutableMap.of(
            "linear-regression", Arrays.asList("principal", "princpal_components", "normalization")
    );

    public CalculationParametersBuilder(CalculationConfiguration config) {
        this.config = config;
    }

    public String getSqlQuery() {
        String fields = getFields();
        String tableName = config.getSelectedTables().size() > 1 ? getDefaultFromTableName() : config.getSelectedTables().get(0).getTableName();
        String joinedTables = getJoinedTables(tableName);
        String where = getFilters();

        return "SELECT " + fields + " FROM " + tableName + " " + joinedTables + " WHERE 1=1 " + where;
    }

    private String getFilters() {
        String where = "";

        for (ColumnCondition condition : config.getColumnConditions()) {
            if (condition.getColumnConditions().size() > 0) {
                where += " AND (";
                ArrayList<String> orFilters = new ArrayList<>();
                for (ColumnCondition orCondition : condition.getColumnConditions()) {
                    orFilters.add(getFilter(orCondition));
                }
                where += StringUtils.join(orFilters, " OR ");
                where += ") ";
            } else {
                where += " AND " + getFilter(condition);
            }
        }
        return where;
    }

    private String getFilter(ColumnCondition condition) {
        return condition.getProperty() + condition.getOperator() + "'" + condition.getValue() + "'";
    }

    private String getFields() {
        HashMap<String, List<ColumnConfig>> groups = slice();
        ArrayList<String> columns = new ArrayList<>();

        for (String s : groupOrders.get(config.getCalculation_id())) {
            for (ColumnConfig columnConfig : groups.get(s)) {
                columns.add(columnConfig.getColumnName());
            }
        }
        return StringUtils.join(columns, ",");
    }

    private String getJoinedTables(String tableName) {
        if (tableName != getDefaultFromTableName()) return "";

        String joinedTables = "";

        for (SelectedTable table : config.getSelectedTables()) {
            String fieldName = keyNames.get(table.getTableName());
            if (fieldName != null) {
                joinedTables += " INNER JOIN " + table.getTableName() + "" +
                        " ON " + tableName + '.' + fieldName + "=" + table.getTableName() + "." + fieldName;
            }

        }
        return joinedTables;
    }

    private String getDefaultFromTableName() {
        return ApplicationConfig.mode == ApplicationConfig.MODE.CEMENT ? "meresi_eredmeny_cement" : "meresi_eredmeny_lezer";
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

        List<String> properties = propertyConfig.get(config.getCalculation_id());
        if (properties == null) return params;


        for (String prop : properties) {
            try {
                Object value = BeanUtils.getProperty(config, prop);
                params.put(prop, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return params;
    }
}

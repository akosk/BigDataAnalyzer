package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Column;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */

@Entity
@javax.persistence.Table(name = "selected_table")
public class SelectedTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dataSourceName;
    private String databaseName;
    private String tableName;

    @ManyToOne
    @JoinColumn(name = "calculation_configuration_id", nullable = false, updatable = false, insertable = true)
    @JsonIgnore
    private CalculationConfiguration calculationConfiguration;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }




    public CalculationConfiguration getCalculationConfiguration() {
        return calculationConfiguration;
    }

    public void setCalculationConfiguration(CalculationConfiguration calculationConfiguration) {
        this.calculationConfiguration = calculationConfiguration;
    }


    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}

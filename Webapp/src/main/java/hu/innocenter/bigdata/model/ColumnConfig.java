package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Ákos Kiszely on 2015.11.24..
 * akos.kiszely@gmail.com
 */

@Entity
@javax.persistence.Table(name = "column_config")
public class ColumnConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tableName;
    private String columnName;
    private String type;

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

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CalculationConfiguration getCalculationConfiguration() {
        return calculationConfiguration;
    }

    public void setCalculationConfiguration(CalculationConfiguration calculationConfiguration) {
        this.calculationConfiguration = calculationConfiguration;
    }
}

package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.23..
 * akos.kiszely@gmail.com
 */
@Entity
@javax.persistence.Table(name = "column_condition")
public class ColumnCondition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private CalculationConfiguration calculationConfiguration;

    @ManyToOne
    @JsonIgnore
    private ColumnCondition columnCondition;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "columnCondition", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ColumnCondition> columnConditions =new ArrayList<>();

    private String property;
    private String operator;
    private String value;


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

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ColumnCondition getColumnCondition() {
        return columnCondition;
    }

    public void setColumnCondition(ColumnCondition columnCondition) {
        this.columnCondition = columnCondition;
    }

    public List<ColumnCondition> getColumnConditions() {
        return columnConditions;
    }

    public void setColumnConditions(List<ColumnCondition> columnConditions) {
        this.columnConditions = columnConditions;
    }
}

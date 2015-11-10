package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.innocenter.bigdata.helpers.JsonDateTimeSerializer;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ákos Kiszely on 2015.11.20..
 * akos.kiszely@gmail.com
 */

@Entity
@javax.persistence.Table(name = "calculation_configuration")
public class CalculationConfiguration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;


    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "calculationConfiguration", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SelectedTable> selectedTables = new ArrayList<>();

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "calculationConfiguration", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ColumnConfig> columnConfigs = new ArrayList<>();

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "calculationConfiguration", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ColumnCondition> columnConditions =new ArrayList<>();

    private Boolean principal;
    private int princpal_components;
    private Boolean normalization;
    private String calculation_id;

    private int job_done;
    private int job_in_progress;


    private Date created;
    private Date updated;

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SelectedTable> getSelectedTables() {
        return selectedTables;
    }

    public void setSelectedTables(List<SelectedTable> selectedTables) {
        this.selectedTables = selectedTables;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public int getPrincpal_components() {
        return princpal_components;
    }

    public void setPrincpal_components(int princpal_components) {
        this.princpal_components = princpal_components;
    }

    public Boolean getNormalization() {
        return normalization;
    }

    public void setNormalization(Boolean normalization) {
        this.normalization = normalization;
    }


    public String getCalculation_id() {
        return calculation_id;
    }

    public void setCalculation_id(String calculation_id) {
        this.calculation_id = calculation_id;
    }

    public int getJob_done() {
        return job_done;
    }

    public void setJob_done(int job_done) {
        this.job_done = job_done;
    }

    public int getJob_in_progress() {
        return job_in_progress;
    }

    public void setJob_in_progress(int job_in_progress) {
        this.job_in_progress = job_in_progress;
    }

    public List<ColumnConfig> getColumnConfigs() {
        return columnConfigs;
    }

    public void setColumnConfigs(List<ColumnConfig> columnConfigs) {
        this.columnConfigs = columnConfigs;
    }

    public List<ColumnCondition> getColumnConditions() {
        return columnConditions;
    }

    public void setColumnConditions(List<ColumnCondition> columnConditions) {
        this.columnConditions = columnConditions;
    }
}

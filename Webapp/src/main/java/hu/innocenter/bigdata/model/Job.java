package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.innocenter.bigdata.helpers.JsonDateTimeSerializer;
import org.hibernate.annotations.Type;
import javax.persistence.Column;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ákos Kiszely on 2015.11.26..
 * akos.kiszely@gmail.com
 */
@Entity
@javax.persistence.Table(name = "job")
public class Job implements Serializable {

    public enum STATUS {WAITING, IN_PROGRESS, FINISHED}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Transient
    private int calculation_configuration_id;

    @Transient
    private String calculation_configuration_name;

    @ManyToOne
    @JoinColumn(name = "calculation_configuration_id", nullable = false, updatable = false, insertable = true)
    @JsonIgnore
    private CalculationConfiguration calculationConfiguration;

    private Date job_start;
    private Date job_end;

    @Column(name="status", length=15)
    @Enumerated(EnumType.STRING)
    private STATUS status;

    @Type(type="text")
    @Column(name = "spark_output", length = 65535)
    private String spark_output;

    private Date created;
    private Date updated;


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

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getJob_start() {
        return job_start;
    }

    public void setJob_start(Date job_start) {
        this.job_start = job_start;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getJob_end() {
        return job_end;
    }

    public void setJob_end(Date job_end) {
        this.job_end = job_end;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getSpark_output() {
        return spark_output;
    }

    public void setSpark_output(String spark_output) {
        this.spark_output = spark_output;
    }

    public int getCalculation_configuration_id() {
        return calculation_configuration_id;
    }

    public void setCalculation_configuration_id(int calculation_configuration_id) {
        this.calculation_configuration_id = calculation_configuration_id;
    }

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

    public String getCalculation_configuration_name() {
        return calculation_configuration_name;
    }

    public void setCalculation_configuration_name(String calculation_configuration_name) {
        this.calculation_configuration_name = calculation_configuration_name;
    }
}

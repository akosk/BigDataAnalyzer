package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.innocenter.bigdata.helpers.JsonDateSerializer;
import hu.innocenter.bigdata.helpers.JsonDateTimeSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;

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

//    private List<hu.innocenter.bigdata.model.Table> tables;
//
//    private String condition;
//
//    private String calculation;

    @Column(name = "data", columnDefinition = "TEXT")
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}

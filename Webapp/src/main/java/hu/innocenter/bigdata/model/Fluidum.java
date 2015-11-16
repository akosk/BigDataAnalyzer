package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.innocenter.bigdata.helpers.JsonDateSerializer;
import hu.innocenter.bigdata.helpers.JsonDateTimeSerializer;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Ákos Kiszely on 2015.11.16..
 * akos.kiszely@gmail.com
 */

@Entity
@Table(name = "fluidum")
public class Fluidum implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String kozetmodell_kod;

    private Double Kw;
    private Double TC;

}

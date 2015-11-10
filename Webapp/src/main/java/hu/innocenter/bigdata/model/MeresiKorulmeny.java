package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.innocenter.bigdata.helpers.JsonDateTimeSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ákos Kiszely on 2015.11.24..
 * akos.kiszely@gmail.com
 */

@Entity
@javax.persistence.Table(name = "meresi_korulmeny")
public class MeresiKorulmeny implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String korulmeny_kod;
    private Date datum;
    private String helyszin_kod;
    private String megvilagitas;
    private Double homerseklet;
    private Double legkori_nyomas;

    private Date created;
    private Date updated;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKorulmeny_kod() {
        return korulmeny_kod;
    }

    public void setKorulmeny_kod(String korulmeny_kod) {
        this.korulmeny_kod = korulmeny_kod;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getHelyszin_kod() {
        return helyszin_kod;
    }

    public void setHelyszin_kod(String helyszin_kod) {
        this.helyszin_kod = helyszin_kod;
    }

    public String getMegvilagitas() {
        return megvilagitas;
    }

    public void setMegvilagitas(String megvilagitas) {
        this.megvilagitas = megvilagitas;
    }

    public Double getHomerseklet() {
        return homerseklet;
    }

    public void setHomerseklet(Double homerseklet) {
        this.homerseklet = homerseklet;
    }

    public Double getLegkori_nyomas() {
        return legkori_nyomas;
    }

    public void setLegkori_nyomas(Double legkori_nyomas) {
        this.legkori_nyomas = legkori_nyomas;
    }
    @JsonSerialize(using=JsonDateTimeSerializer.class)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @JsonSerialize(using=JsonDateTimeSerializer.class)
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

}

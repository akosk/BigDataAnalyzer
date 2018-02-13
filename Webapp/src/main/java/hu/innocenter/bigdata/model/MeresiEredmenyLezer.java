package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.innocenter.bigdata.helpers.JsonDateTimeSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ákos Kiszely on 2015.11.24..
 * akos.kiszely@gmail.com
 */

@Entity
@javax.persistence.Table(name = "meresi_eredmeny_lezer")
public class MeresiEredmenyLezer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String meres_kod;
    private String kozetmodell_kod;
    private String fluidum_kod;
    private String korulmeny_kod;

    private Date meres_kezdesi_ideje;
    private Date meres_befejezesi_ideje;

    private String merest_vegzok_nevei;

    private Double porozitas;
    private Double permeabilitas;
    private Double hovezetokepesseg;
    private Double viszkozitas;
    private Double suruseg;

    private Date created;
    private Date updated;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMeres_kod() {
        return meres_kod;
    }

    public void setMeres_kod(String meres_kod) {
        this.meres_kod = meres_kod;
    }

    public String getKozetmodell_kod() {
        return kozetmodell_kod;
    }

    public void setKozetmodell_kod(String kozetmodell_kod) {
        this.kozetmodell_kod = kozetmodell_kod;
    }

    public String getFluidum_kod() {
        return fluidum_kod;
    }

    public void setFluidum_kod(String fluidum_kod) {
        this.fluidum_kod = fluidum_kod;
    }

    public String getKorulmeny_kod() {
        return korulmeny_kod;
    }

    public void setKorulmeny_kod(String korulmeny_kod) {
        this.korulmeny_kod = korulmeny_kod;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getMeres_kezdesi_ideje() {
        return meres_kezdesi_ideje;
    }

    public void setMeres_kezdesi_ideje(Date meres_kezdesi_ideje) {
        this.meres_kezdesi_ideje = meres_kezdesi_ideje;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getMeres_befejezesi_ideje() {
        return meres_befejezesi_ideje;
    }

    public void setMeres_befejezesi_ideje(Date meres_befejezesi_ideje) {
        this.meres_befejezesi_ideje = meres_befejezesi_ideje;
    }

    public String getMerest_vegzok_nevei() {
        return merest_vegzok_nevei;
    }

    public void setMerest_vegzok_nevei(String merest_vegzok_nevei) {
        this.merest_vegzok_nevei = merest_vegzok_nevei;
    }

    public Double getPorozitas() {
        return porozitas;
    }

    public void setPorozitas(Double porozitas) {
        this.porozitas = porozitas;
    }

    public Double getPermeabilitas() {
        return permeabilitas;
    }

    public void setPermeabilitas(Double permeabilitas) {
        this.permeabilitas = permeabilitas;
    }

    public Double getHovezetokepesseg() {
        return hovezetokepesseg;
    }

    public void setHovezetokepesseg(Double hovezetokepesseg) {
        this.hovezetokepesseg = hovezetokepesseg;
    }

    public Double getViszkozitas() {
        return viszkozitas;
    }

    public void setViszkozitas(Double viszkozitas) {
        this.viszkozitas = viszkozitas;
    }

    public Double getSuruseg() {
        return suruseg;
    }

    public void setSuruseg(Double suruseg) {
        this.suruseg = suruseg;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}

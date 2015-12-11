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

    private String fluidum_kod;

    private String fluidum_megnevezese;

    private Date fluidum_keszitesi_ideje;
    private String fluidum_keszitok_nevei;

    private Double fluidum_komponens_01;
    private Double fluidum_komponens_02;
    private Double fluidum_komponens_03;
    private Double fluidum_komponens_04;
    private Double fluidum_komponens_05;
    private Double fluidum_komponens_06;
    private Double fluidum_komponens_07;
    private Double fluidum_komponens_08;
    private Double fluidum_komponens_09;
    private Double fluidum_komponens_10;
    private Double fluidum_komponens_11;
    private Double fluidum_komponens_12;
    private Double fluidum_komponens_13;
    private Double fluidum_komponens_14;
    private Double fluidum_komponens_15;
    private Double fluidum_komponens_16;

    private String korulmeny_kod;

    private Date meresi_kezdesi_ideje;
    private Date meresi_befejezesi_ideje;

    private String merest_vegzok_nevei;

    private Double suruseg;
    private Double viszkozitas;
    private Double fajho;
    private Double hovezetokepesseg;


    private Date created;
    private Date updated;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFluidum_kod() {
        return fluidum_kod;
    }

    public void setFluidum_kod(String fluidum_kod) {
        this.fluidum_kod = fluidum_kod;
    }

    public Double getFluidum_komponens_01() {
        return fluidum_komponens_01;
    }

    public void setFluidum_komponens_01(Double fluidum_komponens_01) {
        this.fluidum_komponens_01 = fluidum_komponens_01;
    }

    public Double getFluidum_komponens_02() {
        return fluidum_komponens_02;
    }

    public void setFluidum_komponens_02(Double fluidum_komponens_02) {
        this.fluidum_komponens_02 = fluidum_komponens_02;
    }

    public Double getFluidum_komponens_03() {
        return fluidum_komponens_03;
    }

    public void setFluidum_komponens_03(Double fluidum_komponens_03) {
        this.fluidum_komponens_03 = fluidum_komponens_03;
    }

    public Double getFluidum_komponens_04() {
        return fluidum_komponens_04;
    }

    public void setFluidum_komponens_04(Double fluidum_komponens_04) {
        this.fluidum_komponens_04 = fluidum_komponens_04;
    }

    public Double getFluidum_komponens_05() {
        return fluidum_komponens_05;
    }

    public void setFluidum_komponens_05(Double fluidum_komponens_05) {
        this.fluidum_komponens_05 = fluidum_komponens_05;
    }

    public Double getFluidum_komponens_06() {
        return fluidum_komponens_06;
    }

    public void setFluidum_komponens_06(Double fluidum_komponens_06) {
        this.fluidum_komponens_06 = fluidum_komponens_06;
    }

    public Double getFluidum_komponens_07() {
        return fluidum_komponens_07;
    }

    public void setFluidum_komponens_07(Double fluidum_komponens_07) {
        this.fluidum_komponens_07 = fluidum_komponens_07;
    }

    public Double getFluidum_komponens_08() {
        return fluidum_komponens_08;
    }

    public void setFluidum_komponens_08(Double fluidum_komponens_08) {
        this.fluidum_komponens_08 = fluidum_komponens_08;
    }

    public Double getFluidum_komponens_09() {
        return fluidum_komponens_09;
    }

    public void setFluidum_komponens_09(Double fluidum_komponens_09) {
        this.fluidum_komponens_09 = fluidum_komponens_09;
    }

    public Double getFluidum_komponens_10() {
        return fluidum_komponens_10;
    }

    public void setFluidum_komponens_10(Double fluidum_komponens_10) {
        this.fluidum_komponens_10 = fluidum_komponens_10;
    }

    public Double getFluidum_komponens_11() {
        return fluidum_komponens_11;
    }

    public void setFluidum_komponens_11(Double fluidum_komponens_11) {
        this.fluidum_komponens_11 = fluidum_komponens_11;
    }

    public Double getFluidum_komponens_12() {
        return fluidum_komponens_12;
    }

    public void setFluidum_komponens_12(Double fluidum_komponens_12) {
        this.fluidum_komponens_12 = fluidum_komponens_12;
    }

    public Double getFluidum_komponens_13() {
        return fluidum_komponens_13;
    }

    public void setFluidum_komponens_13(Double fluidum_komponens_13) {
        this.fluidum_komponens_13 = fluidum_komponens_13;
    }

    public Double getFluidum_komponens_14() {
        return fluidum_komponens_14;
    }

    public void setFluidum_komponens_14(Double fluidum_komponens_14) {
        this.fluidum_komponens_14 = fluidum_komponens_14;
    }

    public Double getFluidum_komponens_15() {
        return fluidum_komponens_15;
    }

    public void setFluidum_komponens_15(Double fluidum_komponens_15) {
        this.fluidum_komponens_15 = fluidum_komponens_15;
    }

    public Double getFluidum_komponens_16() {
        return fluidum_komponens_16;
    }

    public void setFluidum_komponens_16(Double fluidum_komponens_16) {
        this.fluidum_komponens_16 = fluidum_komponens_16;
    }


    public String getFluidum_megnevezese() {
        return fluidum_megnevezese;
    }

    public void setFluidum_megnevezese(String fluidum_megnevezese) {
        this.fluidum_megnevezese = fluidum_megnevezese;
    }

    @JsonSerialize(using=JsonDateTimeSerializer.class)
    public Date getFluidum_keszitesi_ideje() {
        return fluidum_keszitesi_ideje;
    }

    public void setFluidum_keszitesi_ideje(Date fluidum_keszitesi_ideje) {
        this.fluidum_keszitesi_ideje = fluidum_keszitesi_ideje;
    }

    public String getFluidum_keszitok_nevei() {
        return fluidum_keszitok_nevei;
    }

    public void setFluidum_keszitok_nevei(String fluidum_keszitok_nevei) {
        this.fluidum_keszitok_nevei = fluidum_keszitok_nevei;
    }

    public String getKorulmeny_kod() {
        return korulmeny_kod;
    }

    public void setKorulmeny_kod(String korulmeny_kod) {
        this.korulmeny_kod = korulmeny_kod;
    }

    @JsonSerialize(using=JsonDateTimeSerializer.class)
    public Date getMeresi_kezdesi_ideje() {
        return meresi_kezdesi_ideje;
    }

    public void setMeresi_kezdesi_ideje(Date meresi_kezdesi_ideje) {
        this.meresi_kezdesi_ideje = meresi_kezdesi_ideje;
    }

    @JsonSerialize(using=JsonDateTimeSerializer.class)
    public Date getMeresi_befejezesi_ideje() {
        return meresi_befejezesi_ideje;
    }

    public void setMeresi_befejezesi_ideje(Date meresi_befejezesi_ideje) {
        this.meresi_befejezesi_ideje = meresi_befejezesi_ideje;
    }

    public String getMerest_vegzok_nevei() {
        return merest_vegzok_nevei;
    }

    public void setMerest_vegzok_nevei(String merest_vegzok_nevei) {
        this.merest_vegzok_nevei = merest_vegzok_nevei;
    }

    public Double getSuruseg() {
        return suruseg;
    }

    public void setSuruseg(Double suruseg) {
        this.suruseg = suruseg;
    }

    public Double getViszkozitas() {
        return viszkozitas;
    }

    public void setViszkozitas(Double viszkozitas) {
        this.viszkozitas = viszkozitas;
    }

    public Double getFajho() {
        return fajho;
    }

    public void setFajho(Double fajho) {
        this.fajho = fajho;
    }

    public Double getHovezetokepesseg() {
        return hovezetokepesseg;
    }

    public void setHovezetokepesseg(Double hovezetokepesseg) {
        this.hovezetokepesseg = hovezetokepesseg;
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

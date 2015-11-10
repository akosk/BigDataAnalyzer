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
@javax.persistence.Table(name = "meresi_eredmeny_cement")
public class MeresiEredmenyCement implements Serializable {

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

    private Double cso_hovezetkokepessege;
    private Double cementpalast_hovezetokepessege;
    private Double kozet_hovezetokepessege;
    private Double csofluidum_hovezetokepessege;
    private Double kozetfluidum_hovezetokepessege;
    private Double csofluidum_sebessege;
    private Double kozetfluidum_sebessege;
    private Double csofluidum_belepo_homerseklete;
    private Double csofluidum_kilepo_homerseklete;
    private Double csofluidum_belepo_nyomasa;
    private Double csofluidum_kilepo_nyomasa;
    private Double kozetfluidum_belepo_homerseklete;
    private Double kozetfluidum_kilepo_homerseklete;
    private Double kozetfluidum_belepo_nyomasa;
    private Double kozetfluidum_kilepo_nyomasa;
    private Date ultrahang_jelzes_ideje;

    private Date created;
    private Date updated;

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

    public Double getCso_hovezetkokepessege() {
        return cso_hovezetkokepessege;
    }

    public void setCso_hovezetkokepessege(Double cso_hovezetkokepessege) {
        this.cso_hovezetkokepessege = cso_hovezetkokepessege;
    }

    public Double getCementpalast_hovezetokepessege() {
        return cementpalast_hovezetokepessege;
    }

    public void setCementpalast_hovezetokepessege(Double cementpalast_hovezetokepessege) {
        this.cementpalast_hovezetokepessege = cementpalast_hovezetokepessege;
    }

    public Double getKozet_hovezetokepessege() {
        return kozet_hovezetokepessege;
    }

    public void setKozet_hovezetokepessege(Double kozet_hovezetokepessege) {
        this.kozet_hovezetokepessege = kozet_hovezetokepessege;
    }

    public Double getCsofluidum_hovezetokepessege() {
        return csofluidum_hovezetokepessege;
    }

    public void setCsofluidum_hovezetokepessege(Double csofluidum_hovezetokepessege) {
        this.csofluidum_hovezetokepessege = csofluidum_hovezetokepessege;
    }

    public Double getKozetfluidum_hovezetokepessege() {
        return kozetfluidum_hovezetokepessege;
    }

    public void setKozetfluidum_hovezetokepessege(Double kozetfluidum_hovezetokepessege) {
        this.kozetfluidum_hovezetokepessege = kozetfluidum_hovezetokepessege;
    }

    public Double getCsofluidum_sebessege() {
        return csofluidum_sebessege;
    }

    public void setCsofluidum_sebessege(Double csofluidum_sebessege) {
        this.csofluidum_sebessege = csofluidum_sebessege;
    }

    public Double getKozetfluidum_sebessege() {
        return kozetfluidum_sebessege;
    }

    public void setKozetfluidum_sebessege(Double kozetfluidum_sebessege) {
        this.kozetfluidum_sebessege = kozetfluidum_sebessege;
    }

    public Double getCsofluidum_belepo_homerseklete() {
        return csofluidum_belepo_homerseklete;
    }

    public void setCsofluidum_belepo_homerseklete(Double csofluidum_belepo_homerseklete) {
        this.csofluidum_belepo_homerseklete = csofluidum_belepo_homerseklete;
    }

    public Double getCsofluidum_kilepo_homerseklete() {
        return csofluidum_kilepo_homerseklete;
    }

    public void setCsofluidum_kilepo_homerseklete(Double csofluidum_kilepo_homerseklete) {
        this.csofluidum_kilepo_homerseklete = csofluidum_kilepo_homerseklete;
    }

    public Double getCsofluidum_belepo_nyomasa() {
        return csofluidum_belepo_nyomasa;
    }

    public void setCsofluidum_belepo_nyomasa(Double csofluidum_belepo_nyomasa) {
        this.csofluidum_belepo_nyomasa = csofluidum_belepo_nyomasa;
    }

    public Double getCsofluidum_kilepo_nyomasa() {
        return csofluidum_kilepo_nyomasa;
    }

    public void setCsofluidum_kilepo_nyomasa(Double csofluidum_kilepo_nyomasa) {
        this.csofluidum_kilepo_nyomasa = csofluidum_kilepo_nyomasa;
    }

    public Double getKozetfluidum_belepo_homerseklete() {
        return kozetfluidum_belepo_homerseklete;
    }

    public void setKozetfluidum_belepo_homerseklete(Double kozetfluidum_belepo_homerseklete) {
        this.kozetfluidum_belepo_homerseklete = kozetfluidum_belepo_homerseklete;
    }

    public Double getKozetfluidum_kilepo_homerseklete() {
        return kozetfluidum_kilepo_homerseklete;
    }

    public void setKozetfluidum_kilepo_homerseklete(Double kozetfluidum_kilepo_homerseklete) {
        this.kozetfluidum_kilepo_homerseklete = kozetfluidum_kilepo_homerseklete;
    }

    public Double getKozetfluidum_belepo_nyomasa() {
        return kozetfluidum_belepo_nyomasa;
    }

    public void setKozetfluidum_belepo_nyomasa(Double kozetfluidum_belepo_nyomasa) {
        this.kozetfluidum_belepo_nyomasa = kozetfluidum_belepo_nyomasa;
    }

    public Double getKozetfluidum_kilepo_nyomasa() {
        return kozetfluidum_kilepo_nyomasa;
    }

    public void setKozetfluidum_kilepo_nyomasa(Double kozetfluidum_kilepo_nyomasa) {
        this.kozetfluidum_kilepo_nyomasa = kozetfluidum_kilepo_nyomasa;
    }

    @JsonSerialize(using = JsonDateTimeSerializer.class)
    public Date getUltrahang_jelzes_ideje() {
        return ultrahang_jelzes_ideje;
    }

    public void setUltrahang_jelzes_ideje(Date ultrahang_jelzes_ideje) {
        this.ultrahang_jelzes_ideje = ultrahang_jelzes_ideje;
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

}

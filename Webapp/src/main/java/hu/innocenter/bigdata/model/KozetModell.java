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
 * Created by Ákos Kiszely on 2015.11.05..
 * akos.kiszely@gmail.com
 */

@Entity
@Table(name = "kozetmodell")
public class KozetModell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String kozetmodell_kod;

    @NotNull
    @Digits(integer = 8, fraction = 1)
    @Column(nullable = false)
    private Double kulso_atmero;

    private Double belso_atmero;
    private Double hossz;
    private Double befurasi_melyseg;
    private String furasi_forma;
    private Double homok_frakcio_1;
    private Double homok_frakcio_2;
    private Double homok_frakcio_3;
    private Double homok_frakcio_4;
    private Double homok_frakcio_5;
    private Double homok_frakcio_6;
    private Double homok_frakcio_7;
    private Double homok_frakcio_8;
    private Double homok_frakcio_9;
    private Double homok_frakcio_10;
    private Double homok_frakcio_11;
    private Double homok_frakcio_12;
    private Double anyag_frakcio_1;
    private Double anyag_frakcio_2;
    private Date belso_kozetmag_keszitesi_datum;
    private Double preselesi_nyomas;
    private Double preselesi_homerseklet;
    private java.sql.Time preselesi_ido;
    private String preseles_keszito_neve;
    private Date belso_atmero_keszitesi_datum;
    private String belso_atmero_keszito_neve;
    private Date lezeres_befuras_keszitesi_datum;
    private String lezeres_befuras_keszito_neve;
    private Date kulso_kozetkopeny_keszitesi_datum;
    private String kulso_kozetkopeny_keszito_neve;

    private Date created;
    private Date updated;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKozetmodell_kod() {
        return kozetmodell_kod;
    }

    public void setKozetmodell_kod(String kozetmodell_kod) {
        this.kozetmodell_kod = kozetmodell_kod;
    }

    public Double getKulso_atmero() {
        return kulso_atmero;
    }

    public void setKulso_atmero(Double kulso_atmero) {
        this.kulso_atmero = kulso_atmero;
    }

    public Double getBelso_atmero() {
        return belso_atmero;
    }

    public void setBelso_atmero(Double belso_atmero) {
        this.belso_atmero = belso_atmero;
    }

    public Double getHossz() {
        return hossz;
    }

    public void setHossz(Double hossz) {
        this.hossz = hossz;
    }

    public Double getBefurasi_melyseg() {
        return befurasi_melyseg;
    }

    public void setBefurasi_melyseg(Double befurasi_melyseg) {
        this.befurasi_melyseg = befurasi_melyseg;
    }

    public String getFurasi_forma() {
        return furasi_forma;
    }

    public void setFurasi_forma(String furasi_forma) {
        this.furasi_forma = furasi_forma;
    }

    public Double getHomok_frakcio_1() {
        return homok_frakcio_1;
    }

    public void setHomok_frakcio_1(Double homok_frakcio_1) {
        this.homok_frakcio_1 = homok_frakcio_1;
    }

    public Double getHomok_frakcio_2() {
        return homok_frakcio_2;
    }

    public void setHomok_frakcio_2(Double homok_frakcio_2) {
        this.homok_frakcio_2 = homok_frakcio_2;
    }

    public Double getHomok_frakcio_3() {
        return homok_frakcio_3;
    }

    public void setHomok_frakcio_3(Double homok_frakcio_3) {
        this.homok_frakcio_3 = homok_frakcio_3;
    }

    public Double getHomok_frakcio_4() {
        return homok_frakcio_4;
    }

    public void setHomok_frakcio_4(Double homok_frakcio_4) {
        this.homok_frakcio_4 = homok_frakcio_4;
    }

    public Double getHomok_frakcio_5() {
        return homok_frakcio_5;
    }

    public void setHomok_frakcio_5(Double homok_frakcio_5) {
        this.homok_frakcio_5 = homok_frakcio_5;
    }

    public Double getHomok_frakcio_6() {
        return homok_frakcio_6;
    }

    public void setHomok_frakcio_6(Double homok_frakcio_6) {
        this.homok_frakcio_6 = homok_frakcio_6;
    }

    public Double getHomok_frakcio_7() {
        return homok_frakcio_7;
    }

    public void setHomok_frakcio_7(Double homok_frakcio_7) {
        this.homok_frakcio_7 = homok_frakcio_7;
    }

    public Double getHomok_frakcio_8() {
        return homok_frakcio_8;
    }

    public void setHomok_frakcio_8(Double homok_frakcio_8) {
        this.homok_frakcio_8 = homok_frakcio_8;
    }

    public Double getHomok_frakcio_9() {
        return homok_frakcio_9;
    }

    public void setHomok_frakcio_9(Double homok_frakcio_9) {
        this.homok_frakcio_9 = homok_frakcio_9;
    }

    public Double getHomok_frakcio_10() {
        return homok_frakcio_10;
    }

    public void setHomok_frakcio_10(Double homok_frakcio_10) {
        this.homok_frakcio_10 = homok_frakcio_10;
    }

    public Double getHomok_frakcio_11() {
        return homok_frakcio_11;
    }

    public void setHomok_frakcio_11(Double homok_frakcio_11) {
        this.homok_frakcio_11 = homok_frakcio_11;
    }

    public Double getHomok_frakcio_12() {
        return homok_frakcio_12;
    }

    public void setHomok_frakcio_12(Double homok_frakcio_12) {
        this.homok_frakcio_12 = homok_frakcio_12;
    }

    public Double getAnyag_frakcio_1() {
        return anyag_frakcio_1;
    }

    public void setAnyag_frakcio_1(Double anyag_frakcio_1) {
        this.anyag_frakcio_1 = anyag_frakcio_1;
    }

    public Double getAnyag_frakcio_2() {
        return anyag_frakcio_2;
    }

    public void setAnyag_frakcio_2(Double anyag_frakcio_2) {
        this.anyag_frakcio_2 = anyag_frakcio_2;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getBelso_kozetmag_keszitesi_datum() {
        return belso_kozetmag_keszitesi_datum;
    }

    public void setBelso_kozetmag_keszitesi_datum(Date belso_kozetmag_keszitesi_datum) {
        this.belso_kozetmag_keszitesi_datum = belso_kozetmag_keszitesi_datum;
    }

    public Double getPreselesi_nyomas() {
        return preselesi_nyomas;
    }

    public void setPreselesi_nyomas(Double preselesi_nyomas) {
        this.preselesi_nyomas = preselesi_nyomas;
    }

    public Double getPreselesi_homerseklet() {
        return preselesi_homerseklet;
    }

    public void setPreselesi_homerseklet(Double preselesi_homerseklet) {
        this.preselesi_homerseklet = preselesi_homerseklet;
    }

    public Time getPreselesi_ido() {
        return preselesi_ido;
    }

    public void setPreselesi_ido(Time preselesi_ido) {
        this.preselesi_ido = preselesi_ido;
    }

    public String getPreseles_keszito_neve() {
        return preseles_keszito_neve;
    }

    public void setPreseles_keszito_neve(String preseles_keszito_neve) {
        this.preseles_keszito_neve = preseles_keszito_neve;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getBelso_atmero_keszitesi_datum() {
        return belso_atmero_keszitesi_datum;
    }

    public void setBelso_atmero_keszitesi_datum(Date belso_atmero_keszitesi_datum) {
        this.belso_atmero_keszitesi_datum = belso_atmero_keszitesi_datum;
    }

    public String getBelso_atmero_keszito_neve() {
        return belso_atmero_keszito_neve;
    }

    public void setBelso_atmero_keszito_neve(String belso_atmero_keszito_neve) {
        this.belso_atmero_keszito_neve = belso_atmero_keszito_neve;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getLezeres_befuras_keszitesi_datum() {
        return lezeres_befuras_keszitesi_datum;
    }

    public void setLezeres_befuras_keszitesi_datum(Date lezeres_befuras_keszitesi_datum) {
        this.lezeres_befuras_keszitesi_datum = lezeres_befuras_keszitesi_datum;
    }

    public String getLezeres_befuras_keszito_neve() {
        return lezeres_befuras_keszito_neve;
    }

    public void setLezeres_befuras_keszito_neve(String lezeres_befuras_keszito_neve) {
        this.lezeres_befuras_keszito_neve = lezeres_befuras_keszito_neve;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getKulso_kozetkopeny_keszitesi_datum() {
        return kulso_kozetkopeny_keszitesi_datum;
    }

    public void setKulso_kozetkopeny_keszitesi_datum(Date kulso_kozetkopeny_keszitesi_datum) {
        this.kulso_kozetkopeny_keszitesi_datum = kulso_kozetkopeny_keszitesi_datum;
    }

    public String getKulso_kozetkopeny_keszito_neve() {
        return kulso_kozetkopeny_keszito_neve;
    }

    public void setKulso_kozetkopeny_keszito_neve(String kulso_kozetkopeny_keszito_neve) {
        this.kulso_kozetkopeny_keszito_neve = kulso_kozetkopeny_keszito_neve;
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

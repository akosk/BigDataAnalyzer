package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import hu.innocenter.bigdata.helpers.JsonDateSerializer;
import hu.innocenter.bigdata.helpers.JsonDateTimeSerializer;

import javax.persistence.Column;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Ákos Kiszely on 2015.11.05..
 * akos.kiszely@gmail.com
 */

@Entity
@Table(name = "cementes_kozetmodell")
public class CementesKozetModell implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String kozetmodell_kod;

    private Double kulso_kozetkopeny_atmero;
    private Double belso_kozetmag_kulso_atmero;

    private Double belso_furat_atmero;
    private Double acelcso_kulso_atmero;
    private Double acelcso_belso_atmero;
    private Double acelcso_kod;

    private Double hossz;

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
    private Double cement_komponens_01;
    private Double cement_komponens_02;
    private Double cement_komponens_03;
    private Double cement_komponens_04;
    private Double cement_komponens_05;
    private Double cement_komponens_06;
    private Double cement_komponens_07;
    private Double cement_komponens_08;
    private Double cement_komponens_09;
    private Double cement_komponens_10;
    private Double cement_komponens_11;
    private Double cement_komponens_12;

    private Date belso_kozetmag_keszitesi_datum;
    private Double preselesi_nyomas;
    private Double preselesi_homerseklet;
    private Time preselesi_ido;
    private String preseles_keszito_neve;

    private Date belso_furat_keszitesi_datum;
    private String belso_furat_keszito_neve;

    private Date cementpalast_keszitesi_datum;
    private String cementpalast_keszito_neve;
    private Date kulso_kozetkopeny_keszitesi_datum;
    private String kulso_kozetkopeny_keszito_neve;

    private Date created;
    private Date updated;

    private Integer deleted;


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


    public Double getHossz() {
        return hossz;
    }

    public void setHossz(Double hossz) {
        this.hossz = hossz;
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


    public Double getKulso_kozetkopeny_atmero() {
        return kulso_kozetkopeny_atmero;
    }

    public void setKulso_kozetkopeny_atmero(Double kulso_kozetkopeny_atmero) {
        this.kulso_kozetkopeny_atmero = kulso_kozetkopeny_atmero;
    }

    public Double getBelso_kozetmag_kulso_atmero() {
        return belso_kozetmag_kulso_atmero;
    }

    public void setBelso_kozetmag_kulso_atmero(Double belso_kozetmag_kulso_atmero) {
        this.belso_kozetmag_kulso_atmero = belso_kozetmag_kulso_atmero;
    }

    public Double getBelso_furat_atmero() {
        return belso_furat_atmero;
    }

    public void setBelso_furat_atmero(Double belso_furat_atmero) {
        this.belso_furat_atmero = belso_furat_atmero;
    }



    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getBelso_furat_keszitesi_datum() {
        return belso_furat_keszitesi_datum;
    }

    public void setBelso_furat_keszitesi_datum(Date belso_furat_keszitesi_datum) {
        this.belso_furat_keszitesi_datum = belso_furat_keszitesi_datum;
    }

    public String getBelso_furat_keszito_neve() {
        return belso_furat_keszito_neve;
    }

    public void setBelso_furat_keszito_neve(String belso_furat_keszito_neve) {
        this.belso_furat_keszito_neve = belso_furat_keszito_neve;
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

    public Double getAcelcso_kulso_atmero() {
        return acelcso_kulso_atmero;
    }

    public void setAcelcso_kulso_atmero(Double acelcso_kulso_atmero) {
        this.acelcso_kulso_atmero = acelcso_kulso_atmero;
    }

    public Double getAcelcso_belso_atmero() {
        return acelcso_belso_atmero;
    }

    public void setAcelcso_belso_atmero(Double acelcso_belso_atmero) {
        this.acelcso_belso_atmero = acelcso_belso_atmero;
    }

    public Double getAcelcso_kod() {
        return acelcso_kod;
    }

    public void setAcelcso_kod(Double acelcso_kod) {
        this.acelcso_kod = acelcso_kod;
    }

    public Double getCement_komponens_01() {
        return cement_komponens_01;
    }

    public void setCement_komponens_01(Double cement_komponens_01) {
        this.cement_komponens_01 = cement_komponens_01;
    }

    public Double getCement_komponens_02() {
        return cement_komponens_02;
    }

    public void setCement_komponens_02(Double cement_komponens_02) {
        this.cement_komponens_02 = cement_komponens_02;
    }

    public Double getCement_komponens_03() {
        return cement_komponens_03;
    }

    public void setCement_komponens_03(Double cement_komponens_03) {
        this.cement_komponens_03 = cement_komponens_03;
    }

    public Double getCement_komponens_04() {
        return cement_komponens_04;
    }

    public void setCement_komponens_04(Double cement_komponens_04) {
        this.cement_komponens_04 = cement_komponens_04;
    }

    public Double getCement_komponens_05() {
        return cement_komponens_05;
    }

    public void setCement_komponens_05(Double cement_komponens_05) {
        this.cement_komponens_05 = cement_komponens_05;
    }

    public Double getCement_komponens_06() {
        return cement_komponens_06;
    }

    public void setCement_komponens_06(Double cement_komponens_06) {
        this.cement_komponens_06 = cement_komponens_06;
    }

    public Double getCement_komponens_07() {
        return cement_komponens_07;
    }

    public void setCement_komponens_07(Double cement_komponens_07) {
        this.cement_komponens_07 = cement_komponens_07;
    }

    public Double getCement_komponens_08() {
        return cement_komponens_08;
    }

    public void setCement_komponens_08(Double cement_komponens_08) {
        this.cement_komponens_08 = cement_komponens_08;
    }

    public Double getCement_komponens_09() {
        return cement_komponens_09;
    }

    public void setCement_komponens_09(Double cement_komponens_09) {
        this.cement_komponens_09 = cement_komponens_09;
    }

    public Double getCement_komponens_10() {
        return cement_komponens_10;
    }

    public void setCement_komponens_10(Double cement_komponens_10) {
        this.cement_komponens_10 = cement_komponens_10;
    }

    public Double getCement_komponens_11() {
        return cement_komponens_11;
    }

    public void setCement_komponens_11(Double cement_komponens_11) {
        this.cement_komponens_11 = cement_komponens_11;
    }

    public Double getCement_komponens_12() {
        return cement_komponens_12;
    }

    public void setCement_komponens_12(Double cement_komponens_12) {
        this.cement_komponens_12 = cement_komponens_12;
    }

    @JsonSerialize(using=JsonDateSerializer.class)
    public Date getCementpalast_keszitesi_datum() {
        return cementpalast_keszitesi_datum;
    }

    public void setCementpalast_keszitesi_datum(Date cementpalast_keszitesi_datum) {
        this.cementpalast_keszitesi_datum = cementpalast_keszitesi_datum;
    }

    public String getCementpalast_keszito_neve() {
        return cementpalast_keszito_neve;
    }

    public void setCementpalast_keszito_neve(String cementpalast_keszito_neve) {
        this.cementpalast_keszito_neve = cementpalast_keszito_neve;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}

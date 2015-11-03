package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ákos on 2015.10.27..
 */
public class Column {

    private String sqlName;
    private String sqlType;

    @JsonProperty("sql_name")
    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    @JsonProperty("sql_type")
    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }
}

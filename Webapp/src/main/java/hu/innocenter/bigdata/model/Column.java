package hu.innocenter.bigdata.model;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Ákos on 2015.10.27..
 */
public class Column {

    @XmlElement(name="sql_name")
    public String sqlName;
    @XmlElement(name="sql_type")
    public String sqlType;

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }
}

package hu.innocenter.bigdata.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Ákos on 2015.10.27..
 */
public class DataBase {


    @XmlElement(name = "sql_name")
    private String sqlName;
    @XmlElement(name = "tables")
    private List<Table> tables;


    public String getSqlName() {
        return sqlName;
    }


    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}

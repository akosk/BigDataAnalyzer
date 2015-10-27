package hu.innocenter.bigdata.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Created by Ákos on 2015.10.27..
 */
public class Table {

    @XmlElement(name="sql_name")
    private String sqlName;
    private List<Column> columns;


    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}

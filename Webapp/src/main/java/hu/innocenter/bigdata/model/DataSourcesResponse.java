package hu.innocenter.bigdata.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Ákos on 2015.10.28..
 */
public class DataSourcesResponse {

    private List<DataSource> datasources;


    public List<DataSource> getDatasources() {
        return datasources;
    }

    public void setDatasources(List<DataSource> datasources) {
        this.datasources = datasources;
    }

}

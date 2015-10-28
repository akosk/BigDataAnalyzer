package hu.innocenter.bigdata.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Ákos on 2015.10.28..
 */
@XmlRootElement
public class DataSourcesResponse {

    public List<DataSource> getDatasources() {
        return datasources;
    }

    public void setDatasources(List<DataSource> datasources) {
        this.datasources = datasources;
    }

    List<DataSource> datasources;
}

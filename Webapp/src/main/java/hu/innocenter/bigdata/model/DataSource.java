package hu.innocenter.bigdata.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by Ákos on 2015.10.27..
 */


public class DataSource {

    private int id;
    private String name;

    private List<DataBase> databases;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DataBase> getDatabases() {
        return databases;
    }

    public void setDatabases(List<DataBase> databases) {
        this.databases = databases;
    }
}

package hu.innocenter.bigdata.repository;


import hu.innocenter.bigdata.model.Column;
import hu.innocenter.bigdata.model.DataBase;
import hu.innocenter.bigdata.model.DataSource;
import hu.innocenter.bigdata.model.Table;

import javax.naming.InitialContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ákos on 2015.10.27..
 */
public class DataSourceRepositoryLocalMySql implements DataSourceRepository {

    private Connection conn;

    @Override
    public List<DataSource> findAllDataSources() {
        List<DataSource> datasources = new ArrayList<DataSource>();
        DataSource dataSource = new DataSource();
        dataSource.setId(1);
        dataSource.setName("Local MySQL (Ákos)");


        conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            InitialContext ctx = new InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("java:comp/env/jdbc/bigdata");


            conn = ds.getConnection();

            st = conn.createStatement();
            rs = st.executeQuery("SHOW DATABASES LIKE '%bigdata%'  ");

            List<DataBase> databases = new ArrayList<DataBase>();
            dataSource.setDatabases(databases);

            while (rs.next()) {
                String databaseName = rs.getString(1);
                DataBase dataBase = new DataBase();
                dataBase.setSqlName(databaseName);
                dataBase.setTables(getTables(databaseName));
                databases.add(dataBase);
            }

            datasources.add(dataSource);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return datasources;
    }

    private List<Table> getTables(String databaseName) throws SQLException {

        List<Table> tables = new ArrayList<Table>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            String query = "SHOW TABLES FROM $database";
            query = query.replace("$database", databaseName);
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                String tableName = rs.getString(1);
                if (!tableName.equals("user")) {
                    Table table = new Table();
                    table.setSqlName(tableName);
                    table.setColumns(getColumns(databaseName, tableName));
                    tables.add(table);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tables;
    }

    private List<Column> getColumns(String databaseName, String tableName) {
        List<Column> columns = new ArrayList<Column>();
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            String query = "SHOW COLUMNS FROM `$table` FROM `$database`";
            query = query.replace("$database", databaseName);
            query = query.replace("$table", tableName);
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Column column = new Column();
                column.setSqlName(rs.getString(1));
                column.setSqlType(rs.getString(2));
                columns.add(column);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pst != null) pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return columns;
    }

}

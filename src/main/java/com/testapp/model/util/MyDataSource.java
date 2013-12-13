package com.testapp.model.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyDataSource {

    /**
     * A singleton that represents a pooled datasource. It is composed of a C3PO
     * pooled datasource. Can be changed to any connect pool provider
     */
    private Properties props;
    private ComboPooledDataSource cpds;
    private static MyDataSource datasource;
    private static Logger log = Logger.getLogger(MyDataSource.class);

    private MyDataSource() throws IOException, SQLException {
        // load datasource properties
        log.info("Reading datasource.properties from classpath");
        props = PropertiesLoader.readProperties("datasource.properties");
        cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(props.getProperty("jdbcUrl"));
        cpds.setUser(props.getProperty("username"));
        cpds.setPassword(props.getProperty("password"));

        cpds.setInitialPoolSize(new Integer((String) props.getProperty("initialPoolSize")));
        cpds.setAcquireIncrement(new Integer((String) props.getProperty("acquireIncrement")));
        cpds.setMaxPoolSize(new Integer((String) props.getProperty("maxPoolSize")));
        cpds.setMinPoolSize(new Integer((String) props.getProperty("minPoolSize")));
        cpds.setMaxStatements(new Integer((String) props.getProperty("maxStatements")));
    }

    public static MyDataSource getInstance() throws IOException, SQLException {
        if (datasource == null) {
            datasource = new MyDataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }
}

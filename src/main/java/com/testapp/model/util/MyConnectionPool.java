package com.testapp.model.util;

import com.testapp.exceptions.AppConnectionException;
import com.testapp.model.dao.IAnswerDAO;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class MyConnectionPool {

    private final Logger log;
    private final BlockingQueue<Connection> pool;
    private String driverClass;
    private String jdbcUrl;
    private String username;
    private String password;
    private int initialPoolSize;

    ///////////////////Singletone///////////////////////////////////////////////////////
    private MyConnectionPool() {
        this.pool = new LinkedBlockingDeque<Connection>();
        this.log = Logger.getLogger(MyConnectionPool.class);
    }

    /**
     * This is a the part of implementation Singleton-pattern of Bill Pugh
     * <p/>
     * see https://en.wikipedia.org/wiki/Singleton_pattern
     */
    private static class MyConnectionPoolHolder {

        public static final MyConnectionPool instance = new MyConnectionPool();
    }

    public static MyConnectionPool getInstance() {
        return MyConnectionPoolHolder.instance;
    }

    /////////////////////////////////////////////////////////////////////////////////

    public Connection getConnection() throws AppConnectionException {
        Connection connection = null;
        try {
            connection = pool.poll(1, TimeUnit.SECONDS);
            if (null == connection) {
                pool.add(createConnection(driverClass, jdbcUrl, username, password));
                connection = pool.poll();
            }
            return connection;
        } catch (InterruptedException e) {
            throw new AppConnectionException("can't get connection cause InterruptedException in pool.poll()", e);
        }
    }

    public void release(Connection connection) throws AppConnectionException {
        try {
            if (!connection.isClosed()) {
                pool.add(connection);
            }
        } catch (SQLException e) {
            throw new AppConnectionException("can't release connection; isClosed() check failed", e);
        }
    }


    private Connection createConnection(String driverClass, String jdbcUrl, String username, String password) throws AppConnectionException {
        try {
            Class.forName(driverClass);
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (ClassNotFoundException e) {
            throw new AppConnectionException("create connection failed: class driverClass not found", e);
        } catch (SQLException e) {
            throw new AppConnectionException("create connection failed: db error in DriverManager.getConnection", e);
        }
    }

    public void initPool(Properties props) throws AppConnectionException {
            this.driverClass = props.getProperty("driverClass");
            this.jdbcUrl = props.getProperty("jdbcUrl");
            this.username = props.getProperty("username");
            this.password = props.getProperty("password");
            this.initialPoolSize = Integer.parseInt(props.getProperty("initialPoolSize"));

            for (int i = 0; i < initialPoolSize; i++) {
                Connection connection = createConnection(driverClass, jdbcUrl, username, password);
                pool.add(connection);
            }
    }

}
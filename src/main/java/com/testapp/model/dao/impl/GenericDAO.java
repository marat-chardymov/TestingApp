package com.testapp.model.dao.impl;

import com.testapp.model.dao.IGenericDao;
import com.testapp.model.util.MyConnectionPool;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;

public abstract class GenericDAO<T> implements IGenericDao<T> {

    protected Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;

    protected void closeEverything(ResultSet rs, Statement stmt,
                                   Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }
        if (con != null) {
            //con.close();
            MyConnectionPool.getInstance().release(con);
        }
    }

    protected void closeSC(Statement stmt,
                           Connection con) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
            }
        }
        if (con != null) {
            //con.close();
           MyConnectionPool.getInstance().release(con);
        }
    }

    protected Connection getConnection() {
//        MyDataSource ds = null;
//        Connection connection = null;
//            ds = MyDataSource.getInstance();
//            connection = ds.getConnection();
        return MyConnectionPool.getInstance().getConnection();
    }
}

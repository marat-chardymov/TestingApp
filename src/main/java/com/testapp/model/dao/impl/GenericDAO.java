package com.testapp.model.dao.impl;

import com.testapp.exceptions.AppConnectionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IGenericDao;
import com.testapp.model.util.MyConnectionPool;

import java.sql.*;

public abstract class GenericDAO<T> implements IGenericDao<T> {

    protected Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected ResultSet resultSet = null;

    protected void closeEverything(ResultSet rs, Statement stmt,
                                   Connection con) throws AppDAOException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new AppDAOException("error in ResultSet's close()", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new AppDAOException("error in Statement's close()", e);
            }
        }
        if (con != null) {
            try {
                MyConnectionPool.getInstance().release(con);
            } catch (AppConnectionException e) {
                throw new AppDAOException("can't release connection", e);
            }
        }
    }

    protected void closeSC(Statement stmt,
                           Connection con) throws AppDAOException {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                 throw new AppDAOException("can't close statement",e);
            }
        }
        if (con != null) {
            try {
                MyConnectionPool.getInstance().release(con);
            } catch (AppConnectionException e) {
                throw new AppDAOException("can't release connection",e);
            }
        }
    }

    protected Connection getConnection() throws AppDAOException {
        try {
            return MyConnectionPool.getInstance().getConnection();
        } catch (AppConnectionException e) {
            throw new AppDAOException("can't get connection",e);
        }
    }
}

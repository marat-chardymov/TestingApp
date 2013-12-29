package com.testapp.model.dao.impl;

import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.entities.Subject;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends GenericDAO<Subject> implements ISubjectDAO {
    @Override
    public void add(Subject subject) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            String insertTableSQL = "INSERT INTO subjects"
                    + "(name) VALUES"
                    + "(?)";
            preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subject.getName());
            //we are trying to get id of inserted record
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating answer failed, no rows affected.");
            }
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                subject.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating answer failed, no generated key obtained.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(generatedKeys, preparedStatement, connection);
        }
    }

    @Override
    public Subject find(Long id) {
        Subject subject = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String findRecordSQL = "SELECT * FROM subjects WHERE  subject_id=?";
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            //String url = "jdbc:mysql://localhost:3306/testingappdb";
            //Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            //connection = DriverManager.getConnection(url, "root", "sesame");
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                subject = new Subject(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return subject;
    }

    @Override
    public void update(Subject subject) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String updateRecordSQL = "UPDATE subjects SET name=? WHERE subject_id=?";
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setLong(2, subject.getId());
            preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String deleteRecordSQL = "DELETE FROM subjects WHERE subject_id=?";
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(deleteRecordSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public List<Subject> findAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String findAllRecordsSQL = "SELECT * FROM subjects";
        List<Subject> subjects = new ArrayList<Subject>();
        try {
//            MyDataSource ds = MyDataSource.getInstance();
//            connection = ds.getConnection();
            String url = "jdbc:mysql://localhost:3306/testingappdb";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, "root", "sesame");
            preparedStatement = connection.prepareStatement(findAllRecordsSQL);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String subjectName = rs.getString("name");
                Long id = rs.getLong("subject_id");
                Subject subject = new Subject(subjectName);
                subject.setId(id);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(rs, preparedStatement, connection);
        }
        return subjects;
    }
}

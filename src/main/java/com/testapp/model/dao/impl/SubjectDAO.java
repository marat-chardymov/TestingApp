package com.testapp.model.dao.impl;

import com.testapp.model.dao.IAnswerDAO;
import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.entities.Subject;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends GenericDAO<Subject> implements ISubjectDAO {
    ///////////////////Singletone///////////////////////////////////////////////////////
    private SubjectDAO() {
    }

    /**
     * This is a the part of implementation Singleton-pattern of Bill Pugh
     * <p/>
     * see https://en.wikipedia.org/wiki/Singleton_pattern
     */
    private static class SubjectDAOHolder {

        public static final ISubjectDAO instance = new SubjectDAO();
    }

    public static ISubjectDAO getInstance() {
        return SubjectDAOHolder.instance;
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public void add(Subject subject) {
        try {
            connection = super.getConnection();
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
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                subject.setId(resultSet.getLong(1));
            } else {
                throw new SQLException("Creating answer failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Subject find(Long id) {
        Subject subject = null;
        String findRecordSQL = "SELECT * FROM subjects WHERE  subject_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                subject = new Subject(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return subject;
    }

    @Override
    public void update(Subject subject) {
        String updateRecordSQL = "UPDATE subjects SET name=? WHERE subject_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setLong(2, subject.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) {
        String deleteRecordSQL = "DELETE FROM subjects WHERE subject_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(deleteRecordSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public List<Subject> findAll() {
        String findAllRecordsSQL = "SELECT name,subject_id FROM subjects";
        List<Subject> subjects = new ArrayList<Subject>();
        try {
            String url = "jdbc:mysql://localhost:3306/testingappdb";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, "root", "sesame");
            //connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findAllRecordsSQL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String subjectName = resultSet.getString("name");
                Long id = resultSet.getLong("subject_id");
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
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return subjects;
    }
}

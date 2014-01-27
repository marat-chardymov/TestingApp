package com.testapp.model.dao.impl;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.entities.Subject;

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
    public void add(Subject subject) throws AppDAOException {
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
                throw new AppDAOException("Creating subject failed, no rows affected.");
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                subject.setId(resultSet.getLong(1));
            } else {
                throw new AppDAOException("Creating subject failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            throw new AppDAOException("add subject failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Subject find(Long id) throws AppDAOException {
        Subject subject = null;
        String findRecordSQL = "SELECT * FROM subjects WHERE subject_id=?";
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
            throw new AppDAOException("find subject failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return subject;
    }

    @Override
    public void update(Subject subject) throws AppDAOException {
        String updateRecordSQL = "UPDATE subjects SET name=? WHERE subject_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setLong(2, subject.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AppDAOException("update subject failed", e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) throws AppDAOException {
        String deleteRecordSQL = "DELETE FROM subjects WHERE subject_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(deleteRecordSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AppDAOException("delete subject failed", e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public List<Subject> findAll() throws AppDAOException {
        String findAllRecordsSQL = "SELECT name,subject_id FROM subjects";
        List<Subject> subjects = new ArrayList<Subject>();
        try {
            connection = super.getConnection();
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
            throw new AppDAOException("findAll() subjects method failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return subjects;
    }

    @Override
    public List<Subject> findPage(int page, int pageSize) throws AppDAOException {
        String findPageSQL = "SELECT name,subject_id FROM subjects LIMIT ?, ?";
        List<Subject> subjects = new ArrayList<Subject>();
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findPageSQL);
            //here we use page-1 because in db pages start from zero
            preparedStatement.setInt(1, (page - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String subjectName = resultSet.getString("name");
                Long id = resultSet.getLong("subject_id");
                Subject subject = new Subject(subjectName);
                subject.setId(id);
                subjects.add(subject);
            }
        } catch (SQLException e) {
            throw new AppDAOException("findPage() subjects method failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return subjects;
    }

    @Override
    public int countRecords() throws AppDAOException {
        return super.countRecords("subjects");
    }
}

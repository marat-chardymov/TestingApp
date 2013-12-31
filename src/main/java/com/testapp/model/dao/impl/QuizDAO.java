package com.testapp.model.dao.impl;

import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO extends GenericDAO<Quiz> implements IQuizDAO {
    @Override
    public void add(Quiz quiz) {
        try {
            connection = super.getConnection();
            String insertTableSQL = "INSERT INTO quizzes"
                    + "(name,fk_subject_id) VALUES"
                    + "(?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, quiz.getName());
            preparedStatement.setLong(2, quiz.getSubjectId());
            //we are trying to get id of inserted record
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating answer failed, no rows affected.");
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                quiz.setId(resultSet.getLong(1));
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
    public Quiz find(Long id) {
        Quiz quiz = null;
        String findRecordSQL = "SELECT * FROM quizzes WHERE  quiz_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Long fk_subject = resultSet.getLong("fk_subject_id");
                quiz = new Quiz(name, fk_subject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return quiz;
    }

    @Override
    public void update(Quiz quiz) {
        String updateRecordSQL = "UPDATE quizzes SET name=? WHERE quiz_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, quiz.getName());
            preparedStatement.setLong(2, quiz.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) {
        String deleteRecordSQL = "DELETE FROM quizzes WHERE quiz_id=?";
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
    public List<Quiz> findBySubject(Subject subject) {
        List<Quiz> quizzes = new ArrayList<Quiz>();
        String findRecordSQL = "SELECT * FROM quizzes WHERE  fk_subject_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, subject.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Long fk_subject = resultSet.getLong("fk_subject_id");
                Quiz quiz = new Quiz(name, fk_subject);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return quizzes;
    }

    @Override
    public List<Quiz> findBySubjectId(Long subjectId) {
        List<Quiz> quizzes = new ArrayList<Quiz>();
        String findRecordSQL = "SELECT * FROM quizzes WHERE  fk_subject_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, subjectId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Long fk_subject = resultSet.getLong("fk_subject_id");
                Quiz quiz = new Quiz(name, fk_subject);
                Long quizId = resultSet.getLong("quiz_id");
                quiz.setId(quizId);
                quizzes.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return quizzes;
    }
}

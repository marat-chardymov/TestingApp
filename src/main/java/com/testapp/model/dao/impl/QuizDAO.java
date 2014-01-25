package com.testapp.model.dao.impl;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.entities.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO extends GenericDAO<Quiz> implements IQuizDAO {
    ///////////////////Singletone///////////////////////////////////////////////////////
    private QuizDAO() {
    }

    /**
     * This is a the part of implementation Singleton-pattern of Bill Pugh
     * <p/>
     * see https://en.wikipedia.org/wiki/Singleton_pattern
     */
    private static class QuizDAOHolder {

        public static final IQuizDAO instance = new QuizDAO();
    }

    public static IQuizDAO getInstance() {
        return QuizDAOHolder.instance;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public void add(Quiz quiz) throws AppDAOException {
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
                throw new AppDAOException("Creating quiz failed, no rows affected.");
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                quiz.setId(resultSet.getLong(1));
            } else {
                throw new AppDAOException("Creating quiz failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            throw new AppDAOException("add quiz failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Quiz find(Long id) throws AppDAOException {
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
                Long quizId = resultSet.getLong("quiz_id");
                quiz.setId(quizId);
            }
        } catch (SQLException e) {
            throw new AppDAOException("find quiz failed",e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return quiz;
    }

    @Override
    public void update(Quiz quiz) throws AppDAOException {
        String updateRecordSQL = "UPDATE quizzes SET name=? WHERE quiz_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, quiz.getName());
            preparedStatement.setLong(2, quiz.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AppDAOException("update quiz failed",e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) throws AppDAOException {
        String deleteRecordSQL = "DELETE FROM quizzes WHERE quiz_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(deleteRecordSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AppDAOException("delete quiz failed",e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public List<Quiz> findBySubjectId(Long subjectId) throws AppDAOException {
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
            throw new AppDAOException("findBySubjectId method failed",e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return quizzes;
    }
}

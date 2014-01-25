package com.testapp.model.dao.impl;


import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.entities.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO extends GenericDAO<Question> implements IQuestionDAO {
    ///////////////////Singletone///////////////////////////////////////////////////////
    private QuestionDAO() {
    }

    /**
     * This is a the part of implementation Singleton-pattern of Bill Pugh
     * <p/>
     * see https://en.wikipedia.org/wiki/Singleton_pattern
     */
    private static class QuestionDAOHolder {

        public static final IQuestionDAO instance = new QuestionDAO();
    }

    public static IQuestionDAO getInstance() {
        return QuestionDAOHolder.instance;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public void add(Question question) throws AppDAOException {
        try {
            connection = super.getConnection();
            String insertTableSQL = "INSERT INTO questions"
                    + "(fk_quiz_id, content) VALUES"
                    + "(?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, question.getQuizId());
            preparedStatement.setString(2, question.getContent());
            //we are trying to get id of inserted record
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new AppDAOException("Creating question failed, no rows affected.");
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                question.setId(resultSet.getLong(1));
            } else {
                throw new AppDAOException("Creating question failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            throw new AppDAOException("add question failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Question find(Long id) throws AppDAOException {
        Question question = null;
        String findRecordSQL = "SELECT * FROM questions WHERE  question_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String content = resultSet.getString("content");
                Long quizId = resultSet.getLong("fk_quiz_id");
                question = new Question(content, quizId);
            }
        } catch (SQLException e) {
            throw new AppDAOException("find question failed",e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return question;
    }

    @Override
    public void update(Question question) throws AppDAOException {
        String updateRecordSQL = "UPDATE questions SET content=? WHERE question_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, question.getContent());
            preparedStatement.setLong(2, question.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AppDAOException("update question failed",e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) throws AppDAOException {
        String deleteRecordSQL = "DELETE FROM questions WHERE question_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(deleteRecordSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AppDAOException("delete question failed",e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public List<Question> findByQuizId(Long quizId) throws AppDAOException {
        List<Question> questions = new ArrayList<Question>();
        String findRecordSQL = "SELECT * FROM questions WHERE  fk_quiz_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, quizId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                Long fkQuiz = resultSet.getLong("fk_quiz_id");
                Question question = new Question(content, fkQuiz);
                Long id = resultSet.getLong("question_id");
                question.setId(id);
                questions.add(question);
            }
        } catch (SQLException e) {
            throw new AppDAOException("findByQuizId method failed",e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return questions;
    }
}

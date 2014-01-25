package com.testapp.model.dao.impl;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IAnswerDAO;
import com.testapp.model.entities.Answer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO extends GenericDAO<Answer> implements IAnswerDAO {

    ///////////////////Singletone///////////////////////////////////////////////////////
    private AnswerDAO() {
    }

    /**
     * This is a the part of implementation Singleton-pattern of Bill Pugh
     * <p/>
     * see https://en.wikipedia.org/wiki/Singleton_pattern
     */
    private static class AnswerDAOHolder {

        public static final IAnswerDAO instance = new AnswerDAO();
    }

    public static IAnswerDAO getInstance() {
        return AnswerDAOHolder.instance;
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public void add(Answer answer) throws AppDAOException {
        try {
            connection = super.getConnection();
            String insertTableSQL = "INSERT INTO answers"
                    + "(content, is_right,fk_question_id) VALUES"
                    + "(?,?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, answer.getContent());
            preparedStatement.setBoolean(2, answer.isRight());
            preparedStatement.setLong(3, answer.getQuestionId());
            //we are trying to get id of inserted record
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new AppDAOException("Creating answer failed, no rows affected.");
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                answer.setId(resultSet.getLong(1));
            } else {
                throw new AppDAOException("Creating answer failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            throw new AppDAOException("add answer failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Answer find(Long id) throws AppDAOException {
        Answer answer = null;
        String findRecordSQL = "SELECT * FROM answers WHERE  answer_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String content = resultSet.getString("content");
                Boolean isRight = resultSet.getBoolean("is_right");
                Long questionId = resultSet.getLong("fk_question_id");
                answer = new Answer(content, isRight, questionId);
                Long answerId = resultSet.getLong("answer_id");
                answer.setId(answerId);
            }
        } catch (SQLException e) {
            throw new AppDAOException("find answer failed",e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return answer;
    }

    @Override
    public void update(Answer answer) throws AppDAOException {
        String updateRecordSQL = "UPDATE answers SET content=?,is_right=? WHERE answer_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, answer.getContent());
            preparedStatement.setBoolean(2, answer.isRight());
            preparedStatement.setLong(3, answer.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new AppDAOException("update answer failed",e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) throws AppDAOException {
        String deleteRecordSQL = "DELETE FROM answers WHERE answer_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(deleteRecordSQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new AppDAOException("delete answer failed",e);
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public List<Answer> findByQuestionId(Long questionId) throws AppDAOException {
        List<Answer> answers = new ArrayList<Answer>();
        String findRecordSQL = "SELECT * FROM answers WHERE  fk_question_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, questionId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                Boolean isRight = resultSet.getBoolean("is_right");
                Long fkQuestion = resultSet.getLong("fk_question_id");
                Answer answer = new Answer(content, isRight, fkQuestion);
                Long answerId = resultSet.getLong("answer_id");
                answer.setId(answerId);
                answers.add(answer);
            }
        } catch (SQLException e) {
            throw new AppDAOException("findByQuestionId method failed",e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return answers;
    }
}

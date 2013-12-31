package com.testapp.model.dao.impl;


import com.testapp.model.dao.IAnswerDAO;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO extends GenericDAO<Question> implements IQuestionDAO {

    @Override
    public void add(Question question) {
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
                throw new SQLException("Creating question failed, no rows affected.");
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                question.setId(resultSet.getLong(1));
            } else {
                throw new SQLException("Creating question failed, no generated key obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Question find(Long id) {
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
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return question;
    }

    @Override
    public void update(Question question) {
        String updateRecordSQL = "UPDATE questions SET content=? WHERE question_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, question.getContent());
            preparedStatement.setLong(2, question.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeSC(preparedStatement, connection);
        }
    }

    @Override
    public void delete(Long id) {
        String deleteRecordSQL = "DELETE FROM questions WHERE question_id=?";
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
    public List<Question> findByQuiz(Quiz quiz) {
        List<Question> questions = new ArrayList<Question>();
        String findRecordSQL = "SELECT * FROM questions WHERE  fk_quiz_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, quiz.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                Long fkQuiz = resultSet.getLong("fk_quiz_id");
                Question question = new Question(content, fkQuiz);
                questions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return questions;
    }

    @Override
    public List<Question> findByQuiz(Long quizId) {
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
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return questions;
    }
}

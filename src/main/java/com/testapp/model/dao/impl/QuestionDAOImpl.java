package com.testapp.model.dao.impl;


import com.testapp.model.dao.IAnswerDAO;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class QuestionDAOImpl extends GenericDAOImpl<Question> implements IQuestionDAO {

    @Override
    public List<Question> findQuestionsByTestId(Long id) {
        return null;
    }

    @Override
    public void add(Question question) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            String insertTableSQL = "INSERT INTO questions"
                    + "(fk_test_id, content) VALUES"
                    + "(?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, question.getQuiz().getId());
            preparedStatement.setString(2, question.getContent());
            //we are trying to get id of inserted record
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating question failed, no rows affected.");
            }
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                question.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating question failed, no generated key obtained.");
            }
            //here we persist answers for this question
            IAnswerDAO answerDAO = new AnswerDAOImpl();
            for (Answer answer : question.getAnswers()) {
                answerDAO.add(answer);
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
    public Question find(Long id) {
        return null;
    }

    @Override
    public void update(Question entity) {

    }

    @Override
    public void delete(Long id) {

    }
}

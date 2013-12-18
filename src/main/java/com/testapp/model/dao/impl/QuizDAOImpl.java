package com.testapp.model.dao.impl;

import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;

public class QuizDAOImpl extends GenericDAOImpl<Quiz> implements IQuizDAO{
    @Override
    public void add(Quiz quiz) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            String insertTableSQL = "INSERT INTO quizzes"
                    + "(content, is_right) VALUES"
                    + "(?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, answer.getContent());
            preparedStatement.setBoolean(2, answer.isRight());
            //we are trying to get id of inserted record
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating answer failed, no rows affected.");
            }
            generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                answer.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating answer failed, no generated key obtained.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(generatedKeys,preparedStatement,connection);
        }
    }

    @Override
    public Quiz find(Long id) {
        return null;
    }

    @Override
    public void update(Quiz entity) {

    }

    @Override
    public void delete(Long id) {

    }
}

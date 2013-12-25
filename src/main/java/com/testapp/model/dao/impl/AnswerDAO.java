package com.testapp.model.dao.impl;

import com.testapp.model.dao.IAnswerDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO extends GenericDAO<Answer> implements IAnswerDAO {
    @Override
    public void add(Answer answer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
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
            super.closeEverything(generatedKeys, preparedStatement, connection);
        }
    }

    @Override
    public Answer find(Long id) {
        Answer answer = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String findRecordSQL = "SELECT * FROM answers WHERE  answer_id=?";
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String content = resultSet.getString("content");
                Boolean isRight = resultSet.getBoolean("is_right");
                Long questionId = resultSet.getLong("fk_question_id");
                answer = new Answer(content, isRight,questionId);
                Long answerId = resultSet.getLong("answer_id");
                answer.setId(answerId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return answer;
    }

    @Override
    public void update(Answer answer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String updateRecordSQL = "UPDATE answers SET content=?,is_right=? WHERE answer_id=?";
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(updateRecordSQL);
            preparedStatement.setString(1, answer.getContent());
            preparedStatement.setBoolean(2, answer.isRight());
            preparedStatement.setLong(3, answer.getId());
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
        String deleteRecordSQL = "DELETE FROM answers WHERE answer_id=?";
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
    public List<Answer> findByQuestion(Question question) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<Answer> answers = new ArrayList<Answer>();
        String findRecordSQL = "SELECT * FROM answers WHERE  fk_question_id=?";
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            //String url = "jdbc:mysql://localhost:3306/testingappdb";
            //Class.forName ("com.mysql.jdbc.Driver").newInstance ();
            //connection = DriverManager.getConnection(url, "root", "sesame");
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setLong(1, question.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                Boolean isRight=resultSet.getBoolean("is_right");
                Long fkQuestion = resultSet.getLong("fk_question_id");
                Answer answer = new Answer(content,isRight, fkQuestion);
                Long answerId = resultSet.getLong("answer_id");
                answer.setId(answerId);
                answers.add(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return answers;
    }
}

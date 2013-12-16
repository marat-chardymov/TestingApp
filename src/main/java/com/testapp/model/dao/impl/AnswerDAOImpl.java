package com.testapp.model.dao.impl;

import com.testapp.model.dao.AnswerDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.util.MyDataSource;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Somebody on 16.12.13.
 */
public class AnswerDAOImpl implements AnswerDAO {
    @Override
    public void add(Answer answer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedKeys = null;
        try {
            MyDataSource ds = MyDataSource.getInstance();
            connection = ds.getConnection();
            String insertTableSQL = "INSERT INTO answers"
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
            if (generatedKeys != null) {
                try {
                    generatedKeys.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
            while (resultSet.next()) {
                String content = resultSet.getString("content");
                Boolean isRight = resultSet.getBoolean("is_right");
                answer = new Answer(content, isRight);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

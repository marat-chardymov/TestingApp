package com.testapp.model.dao.impl;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizResultsDAO;
import com.testapp.model.entities.QuizResult;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuizResultsDAO extends GenericDAO<QuizResult> implements IQuizResultsDAO {

    ///////////////////Singletone///////////////////////////////////////////////////////
    private QuizResultsDAO() {
    }

    /**
     * This is a the part of implementation Singleton-pattern of Bill Pugh
     * <p/>
     * see https://en.wikipedia.org/wiki/Singleton_pattern
     */
    private static class QuizResultsDAOHolder {

        public static final IQuizResultsDAO instance = new QuizResultsDAO();
    }

    public static IQuizResultsDAO getInstance() {
        return QuizResultsDAOHolder.instance;
    }

    /////////////////////////////////////////////////////////////////////////////////

    @Override
    public List<QuizResult> findByQuizId(Long quizId) throws AppDAOException {
        List<QuizResult> resultsList = new ArrayList<QuizResult>();
        String findByQuizIdSQL = "SELECT users.username,quizzes.name,scores,date,quiz_results_id FROM quiz_results JOIN users ON users.user_id=fk_user_id JOIN quizzes ON fk_quiz_id=quizzes.quiz_id WHERE fk_quiz_id=?";
        try {
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(findByQuizIdSQL);
            preparedStatement.setLong(1, quizId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String quizName = resultSet.getString("name");
                int scores = resultSet.getInt("scores");
                Date date = resultSet.getDate("date");
                QuizResult result = new QuizResult(username, quizName, scores, date);
                Long id = resultSet.getLong("quiz_results_id");
                result.setId(id);
                resultsList.add(result);
            }
        } catch (SQLException e) {
            throw new AppDAOException("findByQuizId method failed", e);
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return resultsList;
    }

    @Override
    public void add(QuizResult quizResult) throws AppDAOException {
        try {
            connection = super.getConnection();
            String insertTableSQL = "INSERT INTO quiz_results"
                    + "(fk_user_id,fk_quiz_id,scores) VALUES"
                    + "(?,?,?)";
            preparedStatement = connection.prepareStatement(insertTableSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, quizResult.getUserId());
            preparedStatement.setLong(2, quizResult.getQuizId());
            preparedStatement.setInt(3, quizResult.getScores());
            //we are trying to get id of inserted record
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new AppDAOException("Creating quiz failed, no rows affected.");
            }
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                quizResult.setId(resultSet.getLong(1));
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
    public QuizResult find(Long id) throws AppDAOException {
        return null;
    }

    @Override
    public void update(QuizResult entity) throws AppDAOException {

    }

    @Override
    public void delete(Long id) throws AppDAOException {

    }
}

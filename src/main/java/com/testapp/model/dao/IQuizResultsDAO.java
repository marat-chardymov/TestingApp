package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.entities.QuizResult;

import java.util.List;

public interface IQuizResultsDAO extends IGenericDAO<QuizResult> {
    public List<QuizResult> findByQuizId(Long quizId) throws AppDAOException;
}

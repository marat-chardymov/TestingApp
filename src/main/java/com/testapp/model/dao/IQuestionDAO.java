package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.entities.Question;

import java.util.List;

public interface IQuestionDAO extends IGenericDAO<Question> {
    //should return Question list for specific Quiz
    public List<Question> findByQuizId(Long quizId) throws AppDAOException;
}

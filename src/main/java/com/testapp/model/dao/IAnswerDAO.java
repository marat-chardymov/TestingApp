package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.entities.Answer;

import java.util.List;

public interface IAnswerDAO extends IGenericDAO<Answer> {
    public List<Answer> findByQuestionId(Long questionId) throws AppDAOException;
}

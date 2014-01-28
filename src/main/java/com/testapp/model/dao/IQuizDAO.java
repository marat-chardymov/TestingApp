package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.entities.Quiz;

import java.util.List;

public interface IQuizDAO extends IGenericDAO<Quiz> {
    public List<Quiz> findBySubjectId(Long subjectId) throws AppDAOException;
}

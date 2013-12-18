package com.testapp.model.dao;

import com.testapp.model.entities.Question;

import java.util.List;

public interface IQuestionDAO extends IGenericDao<Question> {
    //should return Question list for specific Quiz
    public List<Question> findQuestionsByTestId(Long id);
}

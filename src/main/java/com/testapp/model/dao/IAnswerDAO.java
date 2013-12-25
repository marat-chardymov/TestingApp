package com.testapp.model.dao;

import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;

import java.util.List;

public interface IAnswerDAO extends IGenericDao<Answer> {
    public List<Answer> findByQuestion(Question question);
}

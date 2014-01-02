package com.testapp.model.dao;

import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;

import java.util.List;

public interface IQuestionDAO extends IGenericDao<Question> {
    //should return Question list for specific Quiz
    public List<Question> findByQuizId(Long quizId);
}

package com.testapp.model.dao.impl;

import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;

import java.util.List;

public class QuizDAO extends GenericDAO<Quiz> implements IQuizDAO{
    @Override
    public void add(Quiz quiz) {

    }

    @Override
    public Quiz find(Long id) {
        return null;
    }

    @Override
    public void update(Quiz entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Quiz> findBySubject(Subject subject) {
        return null;
    }
}

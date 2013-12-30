package com.testapp.model.dao;

import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;

import java.util.List;

public interface IQuizDAO extends IGenericDao<Quiz> {
    public List<Quiz> findBySubject(Subject subject);

    public List<Quiz> findBySubjectId(Long subjectId);
}

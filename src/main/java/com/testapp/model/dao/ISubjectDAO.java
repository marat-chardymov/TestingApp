package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.entities.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Somebody on 19.12.13.
 */
public interface ISubjectDAO extends IGenericDao<Subject> {
    public List<Subject> findAll() throws AppDAOException;
}

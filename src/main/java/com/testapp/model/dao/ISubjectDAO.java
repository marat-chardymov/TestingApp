package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.entities.Subject;

import java.util.List;

public interface ISubjectDAO extends IGenericDAO<Subject> {
    public List<Subject> findAll() throws AppDAOException;

    public List<Subject> findPage(int page, int pageSize) throws AppDAOException;

    public int countRecords() throws AppDAOException;
}

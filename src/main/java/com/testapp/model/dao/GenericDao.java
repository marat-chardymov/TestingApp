package com.testapp.model.dao;

public interface GenericDao<T> {

    T add(T entity);

    T find(Object id);

    T update(T entity);

    void delete(Object id);
}


package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;

public interface IGenericDao<T> {

    /**
     * Make an instance managed and persistent.
     *
     * @param entity
     */
    void add(T entity) throws AppDAOException;

    /**
     * Find by primary key. Search for an entity of the primary key. If the
     * entity instance is contained in the persistence context, it is returned
     * from there.
     *
     * @param id - primary key
     * @return the found entity instance or null if the entity does not exist
     */
    T find(Long id) throws AppDAOException;

    /**
     * Adding the state of the given entity into the current persistence
     * context.
     *
     * @param entity
     * @return
     */
    void update(T entity) throws AppDAOException;

    /**
     * Remove the entity instance.
     *
     * @param id - primary key
     */
    void delete(Long id) throws AppDAOException;

    public int countRecords(String table) throws AppDAOException;
}


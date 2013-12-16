package com.testapp.model.dao;

public interface GenericDao<T> {

    /**
     * Make an instance managed and persistent.
     *
     * @param entity
     */
    void add(T entity);

    /**
     * Find by primary key. Search for an entity of the primary key. If the
     * entity instance is contained in the persistence context, it is returned
     * from there.
     *
     * @param id
     *            - primary key
     * @return the found entity instance or null if the entity does not exist
     */
    T find(Long id);

    /**
     * Adding the state of the given entity into the current persistence
     * context.
     *
     * @param entity
     * @return
     */
    void update(T entity);

    /**
     * Remove the entity instance.
     *
     * @param id
     *            - primary key
     */
    void delete(Long id);
}


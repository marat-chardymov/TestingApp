package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.entities.User;

public interface IUserDAO extends IGenericDAO<User> {

    public User findByUsername(String username) throws AppDAOException;
}

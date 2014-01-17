package com.testapp.model.dao.impl;

import com.testapp.model.dao.IUserDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Role;
import com.testapp.model.entities.User;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends GenericDAO<User> implements IUserDAO {
    ///////////////////Singletone///////////////////////////////////////////////////////
    private UserDAO() {
    }

    /**
     * This is a the part of implementation Singleton-pattern of Bill Pugh
     * <p/>
     * see https://en.wikipedia.org/wiki/Singleton_pattern
     */
    private static class UserDAOHolder {

        public static final IUserDAO instance = new UserDAO();
    }

    public static IUserDAO getInstance() {
        return UserDAOHolder.instance;
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Override
    public User findByUsername(String username) {
        User user = null;
        String findRecordSQL = "SELECT name,surname,email,password,role_name,role_id FROM users JOIN roles WHERE  fk_role_id=role_id AND username=(?)";
        try {
            //connection = super.getConnection();
            String url = "jdbc:mysql://localhost:3306/testingappdb";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, "root", "sesame");
            preparedStatement = connection.prepareStatement(findRecordSQL);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role_name = resultSet.getString("role_name");
                Role role = new Role(role_name);
                Long role_id = resultSet.getLong("role_id");
                role.setId(role_id);
                user = new User(name, surname, email, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            super.closeEverything(resultSet, preparedStatement, connection);
        }
        return user;
    }

    @Override
    public void add(User entity) {

    }

    @Override
    public User find(Long id) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

}

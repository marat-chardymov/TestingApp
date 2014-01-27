package com.testapp.model.dao;

import com.testapp.exceptions.AppConnectionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Subject;
import com.testapp.model.util.MyConnectionPool;
import com.testapp.model.util.PropertiesLoader;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class asdf {
    public static void main(String[] args) throws AppDAOException, IOException, AppConnectionException {
//        String hashed = BCrypt.hashpw("student", BCrypt.gensalt());
//        System.out.println(hashed);
//        System.out.println(BCrypt.checkpw("sesame", hashed));
        int divider=3;
        System.out.println((int)Math.ceil((double)7/divider));
    }
}

package com.testapp.model.dao;


import com.testapp.exceptions.AppConnectionException;
import com.testapp.model.util.MyConnectionPool;
import com.testapp.model.util.PropertiesLoader;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;
import java.util.Properties;

@RunWith(Suite.class)
@Suite.SuiteClasses({AnswerDAOTest.class, QuestionDAOTest.class, QuizDAOTest.class, SubjectDAOTest.class})
public class AllDAOTests {
    @BeforeClass
    public static void initPool() throws AppConnectionException, IOException {
        MyConnectionPool pool = MyConnectionPool.getInstance();
        Properties props = PropertiesLoader.readProperties("datasource.properties");
        pool.initPool(props);
    }
}

package com.testapp.model.dao;

import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;
import org.junit.Test;

/**
 * Created by Somebody on 19.12.13.
 */
public class QuizzesDAOTest {

    @Test
    public void findBySubject() {
        Subject subject1 = new Subject("testSubject1");

        Quiz quiz1 = new Quiz("test1");
        Quiz quiz2 = new Quiz("test2");
        IQuizDAO quizDAO = new QuizDAO();

    }
}

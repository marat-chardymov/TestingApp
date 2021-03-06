package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class QuizDAOTest {

    @Test
    public void add() throws AppDAOException {
        Subject subject = new Subject("test_quiz1_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);
        IQuizDAO quizDAO = QuizDAO.getInstance();
        Quiz quiz = new Quiz("test_quiz1", subject.getId());
        quizDAO.add(quiz);
        assertEquals(quiz.getId().getClass(), Long.class);
    }

    @Test
    public void find() throws AppDAOException {
        Subject subject = new Subject("test_quiz2_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_quiz2", subject.getId());
        IQuizDAO quizDAO = QuizDAO.getInstance();
        quizDAO.add(quiz);
        Quiz theSameQuiz = quizDAO.find(quiz.getId());
        assertNotNull(theSameQuiz);
        assertEquals(theSameQuiz.getName(), "test_quiz2");
    }

    @Test
    public void update() throws AppDAOException {
        Subject subject = new Subject("test_quiz3_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_quiz3", subject.getId());
        IQuizDAO quizDAO = QuizDAO.getInstance();
        quizDAO.add(quiz);

        quiz.setName("test_quiz3_updated");
        quizDAO.update(quiz);

        Quiz quizUpdated = quizDAO.find(quiz.getId());
        assertNotNull(quizUpdated);
        assertEquals(quizUpdated.getName(), "test_quiz3_updated");
    }

    @Test
    public void delete() throws AppDAOException {
        Subject subject = new Subject("test_quiz4_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_quiz4", subject.getId());
        IQuizDAO quizDAO = QuizDAO.getInstance();
        quizDAO.add(quiz);
        quizDAO.delete(quiz.getId());
        Quiz deletedQuiz = quizDAO.find(quiz.getId());
        assertNull(deletedQuiz);
    }

    @Test
    public void findBySubject() throws AppDAOException {
        Subject subject = new Subject("test_quiz4_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);

        Quiz quiz1 = new Quiz("test_quiz5_1", subject.getId());
        Quiz quiz2 = new Quiz("test_quiz5_3", subject.getId());
        Quiz quiz3 = new Quiz("test_quiz5_3", subject.getId());
        IQuizDAO quizDAO = QuizDAO.getInstance();
        quizDAO.add(quiz1);
        quizDAO.add(quiz2);
        quizDAO.add(quiz3);

        List<Quiz> foundedQuizzes = quizDAO.findBySubjectId(subject.getId());
        for (Quiz foundedQuiz : foundedQuizzes) {
            assertEquals(foundedQuiz.getSubjectId(), subject.getId());
        }
    }
}

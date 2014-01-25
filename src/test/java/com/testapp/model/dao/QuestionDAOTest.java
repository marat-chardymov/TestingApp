package com.testapp.model.dao;


import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class QuestionDAOTest {
    @Test
    public void add() {
        Subject subject = new Subject("test_question1_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        try {
            subjectDAO.add(subject);

            Quiz quiz = new Quiz("test_question1_quiz1", subject.getId());
            IQuizDAO quizDAO = QuizDAO.getInstance();
            quizDAO.add(quiz);

            Question question = new Question("question1 ?", quiz.getId());
            IQuestionDAO questionDAO = QuestionDAO.getInstance();
            questionDAO.add(question);
            assertEquals(question.getId().getClass(), Long.class);
        } catch (AppDAOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void find() {
        Subject subject = new Subject("test_question2_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        try {
            subjectDAO.add(subject);

            Quiz quiz = new Quiz("test_question2_quiz", subject.getId());
            IQuizDAO quizDAO = QuizDAO.getInstance();
            quizDAO.add(quiz);

            Question question = new Question("question2 ?", quiz.getId());
            IQuestionDAO questionDAO = QuestionDAO.getInstance();
            questionDAO.add(question);

            Question theSameQuestion = questionDAO.find(question.getId());
            assertNotNull(theSameQuestion);
            assertEquals(theSameQuestion.getContent(), question.getContent());
        } catch (AppDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        Subject subject = new Subject("test_question3_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        try {
            subjectDAO.add(subject);

            Quiz quiz = new Quiz("test_question3_quiz", subject.getId());
            IQuizDAO quizDAO = QuizDAO.getInstance();
            quizDAO.add(quiz);

            Question question = new Question("question3 ?", quiz.getId());
            IQuestionDAO questionDAO = QuestionDAO.getInstance();
            questionDAO.add(question);

            question.setContent("question3_updated");
            questionDAO.update(question);

            Question questionUpdated = questionDAO.find(question.getId());
            assertNotNull(questionUpdated);
            assertEquals(questionUpdated.getContent(), question.getContent());
        } catch (AppDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        Subject subject = new Subject("test_question4_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        try {
            subjectDAO.add(subject);

            Quiz quiz = new Quiz("test_question4_quiz", subject.getId());
            IQuizDAO quizDAO = QuizDAO.getInstance();
            quizDAO.add(quiz);

            Question question = new Question("question4 ?", quiz.getId());
            IQuestionDAO questionDAO = QuestionDAO.getInstance();
            questionDAO.add(question);

            questionDAO.delete(question.getId());
            Question deletedQuestion = questionDAO.find(question.getId());
            assertNull(deletedQuestion);
        } catch (AppDAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByQuiz() {
        Subject subject = new Subject("test_question5_subject");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        try {
            subjectDAO.add(subject);

            Quiz quiz = new Quiz("test_question5_quiz", subject.getId());
            IQuizDAO quizDAO = QuizDAO.getInstance();
            quizDAO.add(quiz);

            Question question1 = new Question("test_question5_1", quiz.getId());
            Question question2 = new Question("test_question5_2", quiz.getId());
            Question question3 = new Question("test_question5_3", quiz.getId());
            IQuestionDAO questionDAO = QuestionDAO.getInstance();
            questionDAO.add(question1);
            questionDAO.add(question2);
            questionDAO.add(question3);

            List<Question> foundedQuestions = questionDAO.findByQuizId(quiz.getId());
            assertNotNull(foundedQuestions);
            for (Question fquestion : foundedQuestions) {
                assertEquals(fquestion.getQuizId(), quiz.getId());
            }
        } catch (AppDAOException e) {
            e.printStackTrace();
        }
    }
}

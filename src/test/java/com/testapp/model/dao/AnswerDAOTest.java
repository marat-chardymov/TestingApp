package com.testapp.model.dao;

import com.testapp.model.dao.impl.AnswerDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AnswerDAOTest {
    @Test
    public void add() {
        Subject subject = new Subject("test_answer1_subject");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_answer1_quiz", subject.getId());
        IQuizDAO quizDAO = new QuizDAO();
        quizDAO.add(quiz);

        Question question = new Question("test_answer1_question", quiz.getId());
        IQuestionDAO questionDAO = new QuestionDAO();
        questionDAO.add(question);

        Answer answer = new Answer("1812 year", true, question.getId());
        IAnswerDAO answerDAO = new AnswerDAO();
        answerDAO.add(answer);

        assertEquals(answer.getId().getClass(), Long.class);
    }

    @Test
    public void find() {
        Subject subject = new Subject("test_answer2_subject");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_answer2_quiz", subject.getId());
        IQuizDAO quizDAO = new QuizDAO();
        quizDAO.add(quiz);

        Question question = new Question("test_answer2_question", quiz.getId());
        IQuestionDAO questionDAO = new QuestionDAO();
        questionDAO.add(question);

        Answer answer = new Answer("1812 year", true, question.getId());
        IAnswerDAO answerDAO = new AnswerDAO();
        answerDAO.add(answer);

        assertEquals(answer.getId().getClass(), Long.class);
        Answer theSameAnswer = answerDAO.find(answer.getId());
        assertNotNull(theSameAnswer);
        assertEquals(answer.getContent(), "1812 year");
    }

    @Test
    public void findFail() {
        IAnswerDAO answerDAO = new AnswerDAO();
        Answer answer = answerDAO.find(Long.MAX_VALUE);
        assertNull(answer);
    }

    @Test
    public void update() {
        Subject subject = new Subject("test_answer3_subject");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_answer3_quiz", subject.getId());
        IQuizDAO quizDAO = new QuizDAO();
        quizDAO.add(quiz);

        Question question = new Question("test_answer3_question", quiz.getId());
        IQuestionDAO questionDAO = new QuestionDAO();
        questionDAO.add(question);

        Answer answer = new Answer("1812 year", true, question.getId());
        IAnswerDAO answerDAO = new AnswerDAO();
        answerDAO.add(answer);

        answer.setContent("1945 year");
        answer.setRight(false);
        answerDAO.update(answer);
        answer = answerDAO.find(answer.getId());
        assertNotNull(answer);
        assertEquals(answer.getContent(), "1945 year");
    }

    @Test
    public void delete() {
        Subject subject = new Subject("test_answer4_subject");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_answer4_quiz", subject.getId());
        IQuizDAO quizDAO = new QuizDAO();
        quizDAO.add(quiz);

        Question question = new Question("test_answer4_question", quiz.getId());
        IQuestionDAO questionDAO = new QuestionDAO();
        questionDAO.add(question);
        assertEquals(question.getId().getClass(), Long.class);

        Answer answer = new Answer("999 year", true, question.getId());
        IAnswerDAO answerDAO = new AnswerDAO();
        answerDAO.add(answer);

        answerDAO.delete(answer.getId());
        Answer deletedAnswer = answerDAO.find(answer.getId());
        assertNull(deletedAnswer);
    }

    @Test
    public void findByQuestion(){
        Subject subject = new Subject("test_answer5_subject");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);

        Quiz quiz = new Quiz("test_answer5_quiz", subject.getId());
        IQuizDAO quizDAO = new QuizDAO();
        quizDAO.add(quiz);

        Question question = new Question("test_answer5_question", quiz.getId());
        IQuestionDAO questionDAO = new QuestionDAO();
        questionDAO.add(question);

        Answer answer1 = new Answer("test_answer5_1",true, question.getId());
        Answer answer2 = new Answer("test_answer5_2",false, question.getId());
        Answer answer3 = new Answer("test_answer5_3",true, question.getId());
        IAnswerDAO answerDAO = new AnswerDAO();
        answerDAO.add(answer1);
        answerDAO.add(answer2);
        answerDAO.add(answer3);

        List<Answer> foundedAnswers = answerDAO.findByQuestion(question);
        assertNotNull(foundedAnswers);
        for (Answer answer : foundedAnswers) {
            assertEquals(answer.getQuestionId(),question.getId());
        }
    }

}

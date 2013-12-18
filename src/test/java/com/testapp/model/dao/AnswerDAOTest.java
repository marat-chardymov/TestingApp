package com.testapp.model.dao;

import com.testapp.model.dao.impl.AnswerDAOImpl;
import com.testapp.model.entities.Answer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class AnswerDAOTest {
    @Test
    public void add() {
        Answer answer = new Answer("1812 год", true);
        IAnswerDAO answerDAO = new AnswerDAOImpl();
        answerDAO.add(answer);
        assertEquals(answer.getId().getClass(), Long.class);
    }

    @Test
    public void find() {
        Answer answer = new Answer("1812 год", true);
        IAnswerDAO answerDAO = new AnswerDAOImpl();
        answerDAO.add(answer);
        assertEquals(answer.getId().getClass(), Long.class);
        Answer theSameAnswer = answerDAO.find(answer.getId());
        assertNotNull(theSameAnswer);
        assertEquals(answer.getContent(), "1812 год");
    }

    @Test
    public void findFail() {
        IAnswerDAO answerDAO = new AnswerDAOImpl();
        Answer answer = answerDAO.find(Long.MAX_VALUE);
        assertNull(answer);
    }

    @Test
    public void update() {
        Answer answer = new Answer("1812 год", true);
        IAnswerDAO answerDAO = new AnswerDAOImpl();
        answerDAO.add(answer);
        answer.setContent("1945 год");
        answer.setRight(false);
        answerDAO.update(answer);
        answer = answerDAO.find(answer.getId());
        assertNotNull(answer);
        assertEquals(answer.getContent(), "1945 год");
    }

    @Test
    public void delete() {
        Answer answer = new Answer("999 год", true);
        IAnswerDAO answerDAO = new AnswerDAOImpl();
        answerDAO.add(answer);
        answerDAO.delete(answer.getId());
        Answer deletedAnswer= answerDAO.find(answer.getId());
        assertNull(deletedAnswer);
    }

}

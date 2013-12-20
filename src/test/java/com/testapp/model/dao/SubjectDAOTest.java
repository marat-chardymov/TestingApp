package com.testapp.model.dao;

import com.testapp.model.dao.impl.AnswerDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Subject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


public class SubjectDAOTest {
    @Test
    public void add() {
        Subject subject = new Subject("test1");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);
        assertEquals(subject.getId().getClass(), Long.class);
    }

    @Test
    public void find() {
        Subject subject = new Subject("test2");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);
        assertEquals(subject.getId().getClass(), Long.class);
        Subject theSameSubject = subjectDAO.find(subject.getId());
        assertNotNull(theSameSubject);
        assertEquals(subject.getName(), "test2");
    }

    @Test
    public void update() {
        Subject subject = new Subject("test3");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);
        subject.setName("test3_updated");
        subjectDAO.update(subject);
        subject = subjectDAO.find(subject.getId());
        assertNotNull(subject);
        assertEquals(subject.getName(), "test3_updated");
    }

    @Test
    public void delete() {
        Subject subject = new Subject("test4");
        ISubjectDAO subjectDAO = new SubjectDAO();
        subjectDAO.add(subject);
        subjectDAO.delete(subject.getId());
        Subject deletedSubject = subjectDAO.find(subject.getId());
        assertNull(deletedSubject);
    }
}
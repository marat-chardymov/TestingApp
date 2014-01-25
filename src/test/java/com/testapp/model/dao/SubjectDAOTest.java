package com.testapp.model.dao;

import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Subject;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class SubjectDAOTest {
    @Test
    public void add() throws AppDAOException {
        Subject subject = new Subject("test1");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);
        assertEquals(subject.getId().getClass(), Long.class);
    }

    @Test
    public void find() throws AppDAOException {
        Subject subject = new Subject("test2");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);
        assertEquals(subject.getId().getClass(), Long.class);
        Subject theSameSubject = subjectDAO.find(subject.getId());
        assertNotNull(theSameSubject);
        assertEquals(subject.getName(), "test2");
    }

    @Test
    public void update() throws AppDAOException {
        Subject subject = new Subject("test3");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);
        subject.setName("test3_updated");
        subjectDAO.update(subject);
        Subject updated_subject = subjectDAO.find(subject.getId());
        assertNotNull(updated_subject);
        assertEquals(subject.getName(), "test3_updated");
    }

    @Test
    public void delete() throws AppDAOException {
        Subject subject = new Subject("test4");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);
        subjectDAO.delete(subject.getId());
        Subject deletedSubject = subjectDAO.find(subject.getId());
        assertNull(deletedSubject);
    }

    @Test
    public void findAll() throws AppDAOException {
        Subject subject = new Subject("test5");
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        subjectDAO.add(subject);
        List<Subject> subjectList = subjectDAO.findAll();
        assertNotNull(subjectList);
        assertEquals(false, subjectList.isEmpty());
    }
}

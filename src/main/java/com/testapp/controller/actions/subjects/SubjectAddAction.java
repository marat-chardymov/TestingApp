package com.testapp.controller.actions.subjects;

import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubjectAddAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        String subjectName = request.getParameter("subjectName");
        Subject subject = new Subject(subjectName);
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        try {
            subjectDAO.add(subject);
            int numOfPages = Integer.parseInt(request.getParameter("numOfPages"));
            response.sendRedirect(request.getContextPath() + "/jsp/subjects"+"?page="+numOfPages);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in SubjectAddAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in SubjectAddAction", e);
        }
    }
}

package com.testapp.controller.actions.subjects;

import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.dao.impl.SubjectDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SubjectDeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        Long id = Long.valueOf(request.getParameter("subjectId"));
        try {
            subjectDAO.delete(id);
            int currentPage = Integer.parseInt(request.getParameter("currentPage"));
            response.sendRedirect(request.getContextPath() + "/jsp/subjects"+"?page="+currentPage); // We'd like to fire redirect in case of a view change as result of the action (PRG pattern).
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in SubjectDeleteAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in SubjectDeleteAction", e);
        }
    }
}

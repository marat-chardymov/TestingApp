package com.testapp.controllers.actions.subjects;

import com.testapp.controllers.Action;
import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.dao.impl.SubjectDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectDeleteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        Long id = Long.valueOf(request.getParameter("subjectId"));
        subjectDAO.delete(id);
        response.sendRedirect(request.getContextPath()+"/jsp/subjects"); // We'd like to fire redirect in case of a view change as result of the action (PRG pattern).
    }
}

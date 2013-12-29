package com.testapp.controllers.actions.subjects;

import com.testapp.controllers.Action;
import com.testapp.model.dao.impl.SubjectDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectDeleteAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubjectDAO subjectDAO = new SubjectDAO();
        Long id = Long.valueOf(request.getParameter("subjectId"));
        subjectDAO.delete(id);
        return "";
    }
}

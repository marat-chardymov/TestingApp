package com.testapp.controllers.actions.subjects;

import com.testapp.controllers.Action;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubjectIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> subjectList = subjectDAO.findAll();
        request.setAttribute("subjectList", subjectList);
        request.getRequestDispatcher("/jsp/subjects.jsp").forward(request, response);
    }
}

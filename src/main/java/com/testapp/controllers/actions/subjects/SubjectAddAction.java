package com.testapp.controllers.actions.subjects;

import com.testapp.controllers.Action;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubjectAddAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subjectName = request.getParameter("subjectName");
        Subject subject=new Subject(subjectName);
        ISubjectDAO subjectDAO= SubjectDAO.getInstance();
        subjectDAO.add(subject);
        response.sendRedirect(request.getContextPath()+"/jsp/subjects");
    }
}

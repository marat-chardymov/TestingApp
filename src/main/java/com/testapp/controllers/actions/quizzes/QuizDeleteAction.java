package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.dao.impl.SubjectDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizDeleteAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuizDAO quizDAO = new QuizDAO();
        Long id = Long.valueOf(request.getParameter("quizId"));
        quizDAO.delete(id);
        Long subject_id = Long.valueOf(request.getParameter("subject_id"));
        response.sendRedirect("/quizzes" + "?subject_id=" + subject_id);
        //request.getRequestDispatcher("/jsp/subjects.jsp").forward(request, response);
    }
}

package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizAddAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long subjectId = Long.valueOf(request.getParameter("subjectId"));
        String quizName = request.getParameter("quizName");
        Quiz quiz = new Quiz(quizName, subjectId);
        quiz.setSubjectId(subjectId);
        IQuizDAO quizDAO = QuizDAO.getInstance();
        quizDAO.add(quiz);
        response.sendRedirect("/jsp/quizzes?subject_id="+subjectId);
        //request.setAttribute("quiz", quiz);
        //request.getRequestDispatcher("/jsp/quizEdit.jsp").forward(request, response);
    }
}

package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizCreateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long subjectId = Long.valueOf(request.getParameter("subjectId"));
        String quizName = request.getParameter("quizName");
        request.getRequestDispatcher("/jsp/createQuiz.jsp").forward(request, response);
    }
}

package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuizIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IQuizDAO quizDAO = QuizDAO.getInstance();
        Long subjectId = Long.valueOf(request.getParameter("subject_id"));
        List<Quiz> quizList = quizDAO.findBySubjectId(subjectId);
        request.setAttribute("quizList", quizList);
        request.getRequestDispatcher("quizzes.jsp").forward(request, response);
    }
}

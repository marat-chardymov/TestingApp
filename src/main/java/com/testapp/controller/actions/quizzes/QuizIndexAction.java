package com.testapp.controller.actions.quizzes;

import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Quiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuizIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        IQuizDAO quizDAO = QuizDAO.getInstance();
        Long subjectId = Long.valueOf(request.getParameter("subject_id"));
        List<Quiz> quizList = null;
        try {
            quizList = quizDAO.findBySubjectId(subjectId);

        request.setAttribute("quizList", quizList);
        request.getRequestDispatcher("quizzes.jsp").forward(request, response);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in QuizIndexAction", e);
        } catch (ServletException e) {
            throw new AppActionException("ServletException in QuizIndexAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in QuizIndexAction", e);
        }
    }
}

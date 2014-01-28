package com.testapp.controller.actions.quizzes;

import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.impl.QuizDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuizDeleteAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        IQuizDAO quizDAO = QuizDAO.getInstance();
        Long id = Long.valueOf(request.getParameter("quizId"));
        try {
            quizDAO.delete(id);
        Long subject_id = Long.valueOf(request.getParameter("subject_id"));
        response.sendRedirect(request.getContextPath()+"/jsp/quizzes" + "?subject_id=" + subject_id);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in QuizDeleteAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in QuizDeleteAction", e);
        }
    }
}

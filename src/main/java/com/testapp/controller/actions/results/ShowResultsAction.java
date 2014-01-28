package com.testapp.controller.actions.results;

import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizResultsDAO;
import com.testapp.model.dao.impl.QuizResultsDAO;
import com.testapp.model.entities.QuizResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowResultsAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        IQuizResultsDAO quizResultsDAO = QuizResultsDAO.getInstance();
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        List<QuizResult> quizResultList = null;
        try {
            quizResultList = quizResultsDAO.findByQuizId(quizId);
            request.setAttribute("quizResultList", quizResultList);
            request.getRequestDispatcher("/jsp/quizResults.jsp").forward(request, response);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in ShowResultsAction", e);
        } catch (ServletException e) {
            throw new AppActionException("ServletException in ShowResultsAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in ShowResultsAction", e);
        }
    }
}

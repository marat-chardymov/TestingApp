package com.testapp.controllers.actions.questions;

import com.testapp.controllers.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.entities.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        IQuestionDAO questionDAO = QuestionDAO.getInstance();
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        List<Question> questionList = null;
        try {
            questionList = questionDAO.findByQuizId(quizId);
            request.setAttribute("questionList", questionList);
            request.getRequestDispatcher("jsp/questions.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new AppActionException("requestDispatcher's forward() raised ServletException in QuestionIndexAction", e);
        } catch (IOException e) {
            throw new AppActionException("requestDispatcher's forward() raised IOException in QuestionIndexAction", e);
        } catch (AppDAOException e) {
            throw new AppActionException("questionDAO.findByQuizId() raised AppDAOException in QuestionIndexAction", e);
        }
    }
}

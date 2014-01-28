package com.testapp.controller.actions.quizzes;

import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuizAddAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        Long subjectId = Long.valueOf(request.getParameter("subjectId"));
        String quizName = request.getParameter("quizName");
        Quiz quiz = new Quiz(quizName, subjectId);
        quiz.setSubjectId(subjectId);
        IQuizDAO quizDAO = QuizDAO.getInstance();
        try {
            quizDAO.add(quiz);
            response.sendRedirect(request.getContextPath() + "/jsp/quizzes?subject_id=" + subjectId);
        } catch (AppDAOException e) {
            throw new AppActionException("quiz.add() raised AppDAOException in QuizAddAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in QuizAddAction", e);
        }

    }
}

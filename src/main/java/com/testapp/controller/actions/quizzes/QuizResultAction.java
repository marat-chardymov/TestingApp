package com.testapp.controller.actions.quizzes;

import com.testapp.controller.Action;
import com.testapp.controller.util.QuizResultCalc;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizResultsDAO;
import com.testapp.model.dao.impl.QuizResultsDAO;
import com.testapp.model.entities.Quiz;
import com.testapp.model.entities.QuizResult;
import com.testapp.model.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class QuizResultAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        Map<String, String[]> parameters = request.getParameterMap();
        int result = new QuizResultCalc().calculateResult(quiz, parameters);
        try {
            //write result to db
            IQuizResultsDAO quizResultsDAO = QuizResultsDAO.getInstance();
            User user = (User) request.getSession().getAttribute("user");
            QuizResult quizResult = new QuizResult(user.getId(), quiz.getId(), result);
            quizResultsDAO.add(quizResult);

            //show result on jsp
            int questionNumber = quiz.getQuestions().size();
            request.getSession().setAttribute("result", result);
            request.getSession().setAttribute("questionNumber", questionNumber);

            request.getRequestDispatcher("quizResult.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new AppActionException("ServletException in QuizResultAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in QuizResultAction", e);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in QuizResultAction", e);
        }
    }
}

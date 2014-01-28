package com.testapp.controller.actions.quizzes;

import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.impl.AnswerDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuizRunAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        IQuizDAO quizDAO = QuizDAO.getInstance();
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        Quiz quiz = null;
        try {
            quiz = quizDAO.find(quizId);
            List<Question> questionList = QuestionDAO.getInstance().findByQuizId(quiz.getId());
            quiz.setQuestions(questionList);
            for (Question question : questionList) {
                List<Answer> answerList = AnswerDAO.getInstance().findByQuestionId(question.getId());
                question.setAnswers(answerList);
            }
            request.getSession().setAttribute("quiz", quiz);
            request.getRequestDispatcher("quizRun.jsp").forward(request, response);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in QuizRunAction", e);
        } catch (ServletException e) {
            throw new AppActionException("ServletException in QuizRunAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in QuizRunAction", e);
        }
    }
}

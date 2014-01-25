package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;
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

public class QuizEditAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        IQuizDAO quizDAO = QuizDAO.getInstance();
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
            request.setAttribute("cPathVar", request.getContextPath());
            request.getRequestDispatcher("/jsp/quizEdit.jsp").forward(request, response);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in QuizEditAction", e);
        } catch (ServletException e) {
            throw new AppActionException("ServletException in QuizEditAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in QuizEditAction", e);
        }
    }
}

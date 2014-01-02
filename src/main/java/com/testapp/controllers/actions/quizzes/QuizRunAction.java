package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;
import com.testapp.model.dao.impl.AnswerDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuizRunAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuizDAO quizDAO = new QuizDAO();
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        Quiz quiz = quizDAO.find(quizId);
        List<Question> questionList = new QuestionDAO().findByQuizId(quiz.getId());
        quiz.setQuestions(questionList);
        for (Question question : questionList) {
            List<Answer> answerList = new AnswerDAO().findByQuestionId(question.getId());
            question.setAnswers(answerList);
        }
        request.getSession().setAttribute("quiz", quiz);
        request.getRequestDispatcher("/jsp/quizRun.jsp").forward(request, response);
    }
}

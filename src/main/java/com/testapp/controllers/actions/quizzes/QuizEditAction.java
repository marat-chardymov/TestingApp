package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;
import com.testapp.model.dao.IQuizDAO;
import com.testapp.model.dao.impl.AnswerDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuizEditAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        IQuizDAO quizDAO = QuizDAO.getInstance();
        Quiz quiz = quizDAO.find(quizId);

        List<Question> questionList = QuestionDAO.getInstance().findByQuizId(quiz.getId());
        quiz.setQuestions(questionList);
        for (Question question : questionList) {
            List<Answer> answerList = AnswerDAO.getInstance().findByQuestionId(question.getId());
            question.setAnswers(answerList);
        }
        request.getSession().setAttribute("quiz",quiz);
        request.getRequestDispatcher("/jsp/quizEdit.jsp").forward(request, response);
    }
}

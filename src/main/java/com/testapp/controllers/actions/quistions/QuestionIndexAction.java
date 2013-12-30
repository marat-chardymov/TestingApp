package com.testapp.controllers.actions.quistions;

import com.testapp.controllers.Action;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.dao.impl.QuizDAO;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionIndexAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDAO questionDAO = new QuestionDAO();
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        List<Question> questionList = questionDAO.findByQuiz(quizId);
        request.setAttribute("questionList", questionList);
        return "questions";
    }
}

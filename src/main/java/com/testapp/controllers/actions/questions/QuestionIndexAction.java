package com.testapp.controllers.actions.questions;

import com.testapp.controllers.Action;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.entities.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QuestionIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IQuestionDAO questionDAO = QuestionDAO.getInstance();
        Long quizId = Long.valueOf(request.getParameter("quiz_id"));
        List<Question> questionList = questionDAO.findByQuizId(quizId);
        request.setAttribute("questionList", questionList);
        request.getRequestDispatcher("jsp/questions.jsp").forward(request, response);
    }
}

package com.testapp.controllers.actions.questions;

import com.google.gson.Gson;
import com.testapp.controllers.Action;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.entities.Question;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

public class QuizGetJSONAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        IQuestionDAO questionDAO = QuestionDAO.getInstance();
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");

        Gson gson = new Gson();
        String json = gson.toJson(quiz);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}

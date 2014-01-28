package com.testapp.controller.actions.questions;

import com.google.gson.Gson;
import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class QuizGetJSONAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        IQuestionDAO questionDAO = QuestionDAO.getInstance();
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");

        Gson gson = new Gson();
        String json = gson.toJson(quiz);

        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            throw new AppActionException("response.getWriter() raised IO in QuizGetJSONAction", e);
        }
        out.write(json);
        out.flush();
        out.close();
    }
}

package com.testapp.controllers.actions.questions;

import com.google.gson.Gson;
import com.testapp.controllers.Action;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.entities.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class QuestionAddJSONAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        Question question = gson.fromJson(sb.toString(), Question.class);

        IQuestionDAO questionDAO = QuestionDAO.getInstance();
        questionDAO.add(question);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.write(gson.toJson(question));
        out.flush();
        out.close();
    }
}

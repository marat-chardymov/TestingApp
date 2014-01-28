package com.testapp.controller.actions.questions;

import com.google.gson.Gson;
import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.entities.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionAddJSONAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
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
        } catch (IOException e) {
            throw new AppActionException("IO trouble in QuestionAddJSONAction", e);
        } catch (AppDAOException e) {
            throw new AppActionException("questionDAO.add(question) raised AppDAOException in QuestionAddJSONAction", e);
        }
    }
}

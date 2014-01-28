package com.testapp.controller.actions.answers;

import com.google.gson.Gson;
import com.testapp.controller.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.IAnswerDAO;
import com.testapp.model.dao.impl.AnswerDAO;
import com.testapp.model.entities.Answer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AnswerAddJSONAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            Gson gson = new Gson();
            Answer answer = gson.fromJson(sb.toString(), Answer.class);
            IAnswerDAO answerDAO = AnswerDAO.getInstance();
            answerDAO.add(answer);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(answer));
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new AppActionException("IO trouble in AnswerAddJSONAction", e);
        } catch (AppDAOException e) {
            throw new AppActionException("answerDAO.add() raised AppDAOException in AnswerAddJSONAction", e);
        }
    }
}

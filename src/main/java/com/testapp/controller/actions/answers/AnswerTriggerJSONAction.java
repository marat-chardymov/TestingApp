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

public class AnswerTriggerJSONAction implements Action {
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
            Answer answer = gson.fromJson(sb.toString(), Answer.class);
            answer.setRight(!answer.isRight());
            IAnswerDAO answerDAO = AnswerDAO.getInstance();
            answerDAO.update(answer);
        } catch (IOException e) {
            throw new AppActionException("IO trouble in AnswerTriggerJSONAction", e);
        } catch (AppDAOException e) {
            throw new AppActionException("answerDAO.update() raised AppDAOException in AnswerTriggerJSONAction", e);
        }
    }
}

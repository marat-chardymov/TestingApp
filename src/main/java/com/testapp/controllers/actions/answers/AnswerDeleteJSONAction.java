package com.testapp.controllers.actions.answers;


import com.google.gson.Gson;
import com.testapp.controllers.Action;
import com.testapp.model.dao.IAnswerDAO;
import com.testapp.model.dao.IQuestionDAO;
import com.testapp.model.dao.impl.AnswerDAO;
import com.testapp.model.dao.impl.QuestionDAO;
import com.testapp.model.entities.Answer;
import com.testapp.model.entities.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;

public class AnswerDeleteJSONAction implements Action {
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
        Answer answer = gson.fromJson(sb.toString(), Answer.class);

        IAnswerDAO answerDAO= AnswerDAO.getInstance();
        answerDAO.delete(answer.getId());
    }
}

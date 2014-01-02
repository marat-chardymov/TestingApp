package com.testapp.controllers.actions.quizzes;

import com.testapp.controllers.Action;
import com.testapp.controllers.util.QuizResultCalc;
import com.testapp.model.entities.Quiz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class QuizResultAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Quiz quiz = (Quiz) request.getSession().getAttribute("quiz");
        Map<String, String[]> parameters = request.getParameterMap();
        int result = new QuizResultCalc().calculateResult(parameters, quiz);

    }
}

package com.testapp.controllers;

import com.testapp.controllers.actions.home.HomeIndexAction;
import com.testapp.controllers.actions.quistions.QuestionIndexAction;
import com.testapp.controllers.actions.quizzes.QuizDeleteAction;
import com.testapp.controllers.actions.quizzes.QuizIndexAction;
import com.testapp.controllers.actions.quizzes.QuizResultAction;
import com.testapp.controllers.actions.quizzes.QuizRunAction;
import com.testapp.controllers.actions.subjects.SubjectDeleteAction;
import com.testapp.controllers.actions.subjects.SubjectIndexAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    public static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        actions.put("GET/", new HomeIndexAction());

        actions.put("GET/subjects", new SubjectIndexAction());
        actions.put("POST/subjects/delete", new SubjectDeleteAction());

        actions.put("GET/quizzes", new QuizIndexAction());
        actions.put("POST/quizzes/delete", new QuizDeleteAction());
        actions.put("GET/quizRun", new QuizRunAction());
        actions.put("POST/quizResult", new QuizResultAction());

        actions.put("GET/questions", new QuestionIndexAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getRequestURI());
    }
}

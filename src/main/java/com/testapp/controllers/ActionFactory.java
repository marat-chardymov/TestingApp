package com.testapp.controllers;

import com.testapp.controllers.actions.home.HomeIndexAction;
import com.testapp.controllers.actions.quistions.QuestionIndexAction;
import com.testapp.controllers.actions.quizzes.QuizIndexAction;
import com.testapp.controllers.actions.subjects.SubjectDeleteAction;
import com.testapp.controllers.actions.subjects.SubjectIndexAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    public static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        actions.put("GET/",new HomeIndexAction());

        actions.put("GET/subjects", new SubjectIndexAction());
        actions.put("POST/subjects/delete", new SubjectDeleteAction());

        actions.put("GET/quizzes",new QuizIndexAction());
        actions.put("GET/questions", new QuestionIndexAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getRequestURI());
    }
}

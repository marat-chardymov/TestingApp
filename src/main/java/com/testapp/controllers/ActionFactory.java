package com.testapp.controllers;

import com.testapp.controllers.actions.home.HomeIndexAction;
import com.testapp.controllers.actions.quistions.QuestionIndexAction;
import com.testapp.controllers.actions.quizzes.*;
import com.testapp.controllers.actions.subjects.SubjectDeleteAction;
import com.testapp.controllers.actions.subjects.SubjectIndexAction;
import com.testapp.controllers.actions.users.CreateUserAction;
import com.testapp.controllers.actions.users.LoginAction;
import com.testapp.controllers.actions.users.LogoutAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    public static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        actions.put("POST/login", new LoginAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/home", new HomeIndexAction());

        actions.put("GET/subjects", new SubjectIndexAction());
        actions.put("POST/subjects/delete", new SubjectDeleteAction());

        actions.put("GET/quizzes", new QuizIndexAction());
        actions.put("POST/quizzes/create", new QuizCreateAction());
        actions.put("POST/quizzes/delete", new QuizDeleteAction());
        actions.put("GET/quizRun", new QuizRunAction());
        actions.put("POST/quizResult", new QuizResultAction());

        actions.put("GET/questions", new QuestionIndexAction());

        actions.put("POST/user", new CreateUserAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getRequestURI());
    }
}

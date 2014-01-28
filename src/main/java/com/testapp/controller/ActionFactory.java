package com.testapp.controller;

import com.testapp.controller.actions.answers.AnswerAddJSONAction;
import com.testapp.controller.actions.answers.AnswerDeleteJSONAction;
import com.testapp.controller.actions.answers.AnswerTriggerJSONAction;
import com.testapp.controller.actions.home.HomeIndexAction;
import com.testapp.controller.actions.questions.QuestionAddJSONAction;
import com.testapp.controller.actions.questions.QuestionDeleteJSONAction;
import com.testapp.controller.actions.questions.QuestionIndexAction;
import com.testapp.controller.actions.questions.QuizGetJSONAction;
import com.testapp.controller.actions.quizzes.*;
import com.testapp.controller.actions.results.ShowResultsAction;
import com.testapp.controller.actions.subjects.SubjectAddAction;
import com.testapp.controller.actions.subjects.SubjectDeleteAction;
import com.testapp.controller.actions.subjects.SubjectIndexAction;
import com.testapp.controller.actions.users.LoginAction;
import com.testapp.controller.actions.users.LogoutAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    public static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        actions.put("POST/login", new LoginAction());
        actions.put("GET/jsp/logout", new LogoutAction());
        actions.put("GET/jsp/home", new HomeIndexAction());

        actions.put("GET/jsp/subjects", new SubjectIndexAction());
        actions.put("POST/jsp/subjects/delete", new SubjectDeleteAction());
        actions.put("POST/jsp/subjects/add", new SubjectAddAction());

        actions.put("GET/jsp/quizzes", new QuizIndexAction());
        actions.put("POST/jsp/quizzes/add", new QuizAddAction());
        actions.put("GET/jsp/quizzes/edit", new QuizEditAction());
        actions.put("POST/jsp/quizzes/delete", new QuizDeleteAction());
        actions.put("GET/jsp/quizzes/results", new ShowResultsAction());
        actions.put("GET/jsp/quizRun", new QuizRunAction());
        actions.put("POST/jsp/quizResult", new QuizResultAction());

        actions.put("GET/jsp/questions", new QuestionIndexAction());
        actions.put("GET/jsp/questions/getJSON", new QuizGetJSONAction());
        actions.put("POST/jsp/questions/add", new QuestionAddJSONAction());
        actions.put("POST/jsp/questions/delete", new QuestionDeleteJSONAction());

        actions.put("POST/jsp/answers/add", new AnswerAddJSONAction());
        actions.put("POST/jsp/answers/delete", new AnswerDeleteJSONAction());
        actions.put("POST/jsp/answers/triggerIsRight", new AnswerTriggerJSONAction());
    }

    public static Action getAction(HttpServletRequest request) {
            String uriWithoutContext = request.getRequestURI().substring(request.getContextPath().length());
        return actions.get(request.getMethod() + uriWithoutContext);
    }
}

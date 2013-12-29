package com.testapp.controllers;

import com.testapp.controllers.actions.subjects.SubjectDeleteAction;
import com.testapp.controllers.actions.subjects.SubjectIndexAction;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ActionFactory {

    public static Map<String, Action> actions = new HashMap<String, Action>();

    static {
        //actions.put("POST/register", new RegisterAction());
        //actions.put("POST/login", new LoginAction());
        actions.put("GET/subjects", new SubjectIndexAction());
        actions.put("POST/subjects/delete", new SubjectDeleteAction());
        //actions.put("GET/",new IndexPageAction());

    }

    public static Action getAction(HttpServletRequest request) {
        String requestUri = request.getRequestURI();
        if (requestUri.endsWith("/")) {
            //remove last slash character
            requestUri = requestUri.substring(0, requestUri.length() - 1);
            return actions.get(request.getMethod() + requestUri);
        } else
            return actions.get(request.getMethod() + requestUri);
    }
}

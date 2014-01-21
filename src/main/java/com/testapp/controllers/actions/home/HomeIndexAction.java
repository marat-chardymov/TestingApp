package com.testapp.controllers.actions.home;

import com.testapp.controllers.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendRedirect(request.getRequestURI()+".jsp");
    }
}

package com.testapp.controllers.actions.users;

import com.testapp.controllers.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("user");
        response.sendRedirect("/jsp/login.jsp");
    }
}

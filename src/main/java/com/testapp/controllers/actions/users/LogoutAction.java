package com.testapp.controllers.actions.users;

import com.testapp.controllers.Action;
import com.testapp.exceptions.AppActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        request.getSession().removeAttribute("user");
        try {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } catch (IOException e) {
            throw new AppActionException("IOException in LogoutAction", e);
        }
    }
}

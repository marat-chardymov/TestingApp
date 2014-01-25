package com.testapp.controllers.actions.home;

import com.testapp.controllers.Action;
import com.testapp.exceptions.AppActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        try {
            response.sendRedirect(request.getRequestURI()+".jsp");
        } catch (IOException e) {
            throw new AppActionException("IO trouble in HomeIndexAction", e);
        }
    }
}

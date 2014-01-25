package com.testapp.controllers;

import com.testapp.exceptions.AppActionException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        Logger log = Logger.getLogger(FrontController.class);
        try {
            Action action = ActionFactory.getAction(request);
            action.execute(request, response);
        } catch (AppActionException e) {
            log.log(Level.ERROR, e);
        }
    }


}

package com.testapp.controller;

import com.testapp.exceptions.AppException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Logger log = Logger.getLogger(FrontController.class);
        try {
            Action action = ActionFactory.getAction(request);
            action.execute(request, response);
        } catch (AppException e) {
            log.log(Level.ERROR, e);
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request,response);
        }
    }


}

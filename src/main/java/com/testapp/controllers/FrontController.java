package com.testapp.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Action action = ActionFactory.getAction(request);
            action.execute(request, response);

            //if (view.equals(request.getRequestURI().substring(1))) {
                //request.getRequestDispatcher("/jsp/" + view + ".jsp").forward(request, response);

//            } else {
//            }
        } catch (Exception e) {
            throw new ServletException("Executing action failed.", e);
        }
    }
}

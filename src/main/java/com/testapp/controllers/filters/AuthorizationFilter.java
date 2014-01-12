package com.testapp.controllers.filters;

import com.testapp.controllers.Action;
import com.testapp.controllers.actions.users.LoginAction;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorizationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getSession().getAttribute("user") != null || request.getRequestURI().equals("/login")) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect("/login.jsp");
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

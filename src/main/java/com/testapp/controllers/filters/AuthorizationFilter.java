package com.testapp.controllers.filters;

import com.testapp.controllers.Action;
import com.testapp.controllers.actions.users.LoginAction;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthorizationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Logger log = Logger.getLogger(AuthorizationFilter.class);
        try {
            if (request.getSession().getAttribute("user") != null || request.getRequestURI().equals("/login")) {
                chain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
        } catch (ServletException e) {
            log.log(Level.ERROR, "ServletException in AuthorizationFilter", e);
        } catch (IOException e) {
            log.log(Level.ERROR, "IOException in AuthorizationFilter", e);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

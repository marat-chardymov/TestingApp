package com.testapp.controllers.filters;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class CharacterEncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) {
        Logger log = Logger.getLogger(CharacterEncodingFilter.class);
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html; charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            chain.doFilter(req, resp);
        } catch (UnsupportedEncodingException e) {
            log.log(Level.ERROR, "UnsupportedEncodingException in CharacterEncodingFilter", e);
        } catch (ServletException e) {
            log.log(Level.ERROR, "ServletException in CharacterEncodingFilter", e);
        } catch (IOException e) {
            log.log(Level.ERROR, "IOException in CharacterEncodingFilter", e);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

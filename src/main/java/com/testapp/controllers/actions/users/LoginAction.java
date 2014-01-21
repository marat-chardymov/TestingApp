package com.testapp.controllers.actions.users;


import com.testapp.controllers.Action;
import com.testapp.model.dao.IUserDAO;
import com.testapp.model.dao.impl.UserDAO;
import com.testapp.model.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;

public class LoginAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String passCandidate = request.getParameter("password");

        IUserDAO userDAO = UserDAO.getInstance();
        User user = userDAO.findByUsername(username);
        boolean passOk = false;
        if (user != null) {
            passOk = BCrypt.checkpw(passCandidate, user.getPassword());
            if (passOk) {
                //if exists
                request.getSession().setAttribute("user", user);
                response.sendRedirect("jsp/home");
            } else {
                response.sendRedirect("login.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
        }

    }
}

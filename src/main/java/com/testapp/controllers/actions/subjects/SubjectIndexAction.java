package com.testapp.controllers.actions.subjects;

import com.testapp.controllers.Action;
import com.testapp.exceptions.AppActionException;
import com.testapp.exceptions.AppDAOException;
import com.testapp.model.dao.ISubjectDAO;
import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubjectIndexAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException {
        int pageSize = 5;
        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        ISubjectDAO subjectDAO = SubjectDAO.getInstance();
        try {
            //set subjectList
            List<Subject> subjectList = subjectDAO.findPage(currentPage, pageSize);
            request.setAttribute("subjectList", subjectList);

            //set attributes for pagination
            int numOfRecords = subjectDAO.countRecords();
            int numOfPages = (int) Math.ceil((double) numOfRecords / pageSize);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("currentPage", currentPage);

            request.getRequestDispatcher("subjects.jsp").forward(request, response);
        } catch (AppDAOException e) {
            throw new AppActionException("AppDAOException in SubjectIndexAction", e);
        } catch (ServletException e) {
            throw new AppActionException("ServletException in SubjectIndexAction", e);
        } catch (IOException e) {
            throw new AppActionException("IOException in SubjectIndexAction", e);
        }
    }
}

package com.testapp.controllers;

import com.testapp.model.dao.impl.SubjectDAO;
import com.testapp.model.entities.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet("/subjects/*")
public class SubjectsController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> subjectList=subjectDAO.findAll();
        for (Subject subject : subjectList) {
            System.out.println(subject.getName());
        }

        request.setAttribute("subjectList", subjectList );
        request.getRequestDispatcher("/jsp/subjects.jsp").forward(request, response);
    }
}

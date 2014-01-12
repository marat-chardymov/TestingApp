package com.testapp.controllers.actions.quistions;

import com.testapp.controllers.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionAddAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println(request);
    }
}

package com.testapp.controller;

import com.testapp.exceptions.AppActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws AppActionException;
}
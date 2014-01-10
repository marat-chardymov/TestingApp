<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<! DOCTYPE HTML>
<html>
<head>
    <title>Quiz app</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<div class="container" id="header">
    <div id="loginStatus">
        <div id="loggedIn">You logged in as <span class="label label-info">${user.name} ${user.surname}</span></div>
        <a href="/logout" id="logout">Logout</a>
    </div>
    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand">Testing App</a>
            <ul class="nav">
                <li <%if (request.getServletPath().contains("home.jsp")) {%>
                        class="active" <%}%> >
                    <a href="../jsp/home.jsp">Home</a>
                </li>
                <li <%if (request.getServletPath().contains("subjects.jsp")) {%>
                        class="active" <%}%> >
                    <a href="/subjects">Subjects</a>
                </li>
            </ul>
        </div>
    </div>
</div>

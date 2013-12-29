<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<! DOCTYPE HTML>
<html>
<head>
    <title>Quiz app</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body style="padding-top: 20px; ">
<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand">Testing App</a>
            <ul class="nav">
                <li <%if (request.getServletPath().contains("index.jsp")) {%>
                        class="active" <%}%> >
                    <a href="../index.jsp">Home</a>
                </li>
                <li <%if (request.getServletPath().contains("subjects.jsp")) {%>
                        class="active" <%}%> >
                    <a href="/subjects">Subjects</a>
                </li>
            </ul>
        </div>
    </div>
</div>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<! DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/quizResult.css"/>
</head>
<body>
<div class="container-narrow">
    <div class="jumbotron">
        <h1>Your result is ${result} out of ${questionNumber}!</h1>
        <a class="btn btn-large btn-success" href="/jsp/subjects">
            Return to subjects
        </a>
    </div>
</div>
</body>
</html>

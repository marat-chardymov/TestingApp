<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<! DOCTYPE HTML>
<html ng-app>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/quizCreate.css">

    <script src="../js/lib/angular.min.js"></script>
    <script src="../js/lib/underscore-min.js"></script>
    <script type="text/javascript" src="../js/quiz.js"></script>
    <title></title>
</head>
<body>
${param.subjectId}<br>
${param.quizName}
<div ng-controller="QuizCtrl">
    <h2>Total answers: {{getTotalAnswers()}}</h2>
    <ul class="unstyled">
        <li ng-repeat="answer in answers">
            <input type="checkbox" ng-model="answer.isRight">
            <span class="isRight-{{answer.isRight}}">{{answer.content}}</span>
        </li>
    </ul>
    <form class="form-horizontal">
        <input type="text" ng-model="formAnswerText" ng-model-instantly>
        <button class="btn" ng-click="addAnswer()"><i class="icon-plus"></i>Add</button>
    </form>
    <button class="btn large" ng-click="clearFalse()"><i class="icon-trash"></i> Clear false</button>
</div>
</body>
</html>

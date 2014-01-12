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
    <h2>Total answers: {{getTotalQuestions()}}</h2>
    <ul class="unstyled">
        <li ng-repeat="question in questions">
            <span>{{question.id}}</span>
            <span>{{question.content}}</span>
            <button class="btn btn-danger" ng-click="delete()"><i class="icon-trash"></i>delete</button>
        </li>
    </ul>
    <form class="form-horizontal" ng-submit="addQuestion()">
        <input type="text" name="someName" ng-model="formQuestionText" ng-model-instantly>
        <button class="btn" type="submit" value="add"><i class="icon-plus"></i>Add</button>
    </form>

</div>
</body>
</html>

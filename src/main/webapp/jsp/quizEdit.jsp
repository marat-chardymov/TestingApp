<%@ include file="../includes/tagLibs.jsp" %>
<%-- above we include all necessary tag librarise --%>
<! DOCTYPE HTML>
<html ng-app>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icheck/red.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/quiz.css"/>

    <script src="${pageContext.request.contextPath}/js/lib/angular.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lib/underscore-min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/quizCtrl.js"></script>

    <title></title>
</head>
<body>
<div ng-controller="QuizCtrl">

    <div class="container">
        <div class="hero-unit"><h2>${quiz.name}</h2></div>
        <div class="container">
            <form name="questionForm" class="form-horizontal" ng-submit="addQuestion()">
                <fmt:message key="quizEdit.qHolder" var="qHolder"/>
                <input type="text" name="questionText" ng-model="formQuestionText" ng-model-instantly
                       placeholder="${qHolder}" required>
                <button class="btn" id="addQuestion" type="submit" value="add" ng-disabled="!questionForm.$valid"><i
                        class="icon-plus"></i> <fmt:message
                        key="buttons.add"/>
                </button>
            </form>

        </div>

        <div class="container" ng-repeat="question in quiz.questions">
            <div class="content">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th class="span2"><h3><fmt:message key="quizEdit.isRight"/></h3></th>
                        <th class="span6"><h3>{{question.content}}</h3></th>
                        <th class="span2">
                            <button ng-click="deleteQuestion(question)" class="btn"><i class="icon-remove"></i>
                                <fmt:message key="buttons.delete"/>
                            </button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr ng-repeat="answer in question.answers">
                        <td><input type="checkbox" ng-model="answer.isRight" ng-click="triggerIsRight(answer)"></td>
                        <td><strong>{{answer.content}}</strong></td>
                        <td>
                            <button ng-click="deleteAnswer(question,answer)" class="btn"><i class="icon-remove"></i>
                            </button>
                        </td>
                    </tr>

                    </tbody>
                </table>
                <form name="answerForm">
                    <fmt:message key="quizEdit.aHolder" var="aHolder"/>
                    <input type="text" name="answerText" ng-model="formAnswerText" placeholder="${aHolder}" required>
                    <button class="btn" id="addAnswer" ng-click="addAnswer(question,formAnswerText)"
                            ng-disabled="answerForm.answerText.$invalid"><i
                            class="icon-plus"></i>
                    </button>
                </form>
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/jsp/quizzes?subject_id=${quiz.subjectId}"
           class="btn btn-success btn-large" id="backTo"><fmt:message key="quizEdit.backToQ"/></a>
    </div>

</div>
</body>
</html>

<%@ include file="../includes/tagLibs.jsp" %>
<%-- above we include all necessary tag librarise --%>
<! DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/quizResult.css"/>
</head>
<body>
<div class="container-narrow">
    <div class="jumbotron">
        <h1><fmt:message key="quizResult.resultIs"/> ${result} <fmt:message key="quizResult.outOf"/> ${questionNumber}!</h1>
        <a class="btn btn-large btn-success" href="subjects">
            <fmt:message key="quizResult.returnToSubjects"/>
        </a>
    </div>
</div>
</body>
</html>

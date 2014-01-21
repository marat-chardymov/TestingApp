<%@ include file="../includes/tagLibs.jsp" %>
<%-- above we include all necessary tag librarise --%>
<! DOCTYPE HTML>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/icheck/red.css">
    <link rel="stylesheet" href="/css/quiz.css"/>
</head>
<body>
<form action="quizResult" method="post">
    <div class="container">
        <div class="hero-unit"><h2>${quiz.name}</h2></div>
        <c:forEach var="question" items="${quiz.questions}">
            <div class="container">
                <div class="content">
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th class="span1"></th>
                            <th class="span20">${question.content}</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="answer" items="${question.answers}">
                            <tr>
                                <td><input type="checkbox" name="${question.id}" value="${answer.id}"></td>
                                <td><strong>${answer.content}</strong></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:forEach>
        <fmt:message key="buttons.submit" var="submitVar"/>
        <input type="submit" value="${submitVar}" class="btn btn-success btn-large">
    </div>
</form>

<script type="text/javascript" src="/js/lib/jquery-2.0.3.js"></script>
<script type="text/javascript" src="/js/lib/icheck.js"></script>
<script>
    $(document).ready(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-red',
            radioClass: 'iradio_square-red',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>

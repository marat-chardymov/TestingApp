<%@ include file="/includes/header.jsp" %>

<div class="container">
    <div class="container" align="center">
        <table class="table table-hover" style="width: 500px;">
            <!--Here should be jstl forEach-->
            <c:forEach var="quiz" items="${quizList}">
                <tr>
                    <td>${quiz.name}</td>
                    <td>${quiz.id}</td>
                    <td><a href="questions?quiz_id=${quiz.id}" class="btn">get questions</a></td>
                    <td><a href="quizRun?quiz_id=${quiz.id}" class="btn btn-success">start quiz</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="/includes/footer.jsp" %>
<%@ include file="/includes/header.jsp" %>


<div class="container" align="center">
    <form action="quizzes/create" method="post">
        <input type="hidden" name="subjectId" value="${param.subject_id}">
        <input type="text" class="input-large" name="quizName" placeholder="New quiz name...">
        <button type="submit" class="btn btn-info" id="createQuiz">
            <i class="icon-plus"></i> Add
        </button>
    </form>

    <table class="table table-hover">
        <!--Here should be jstl forEach-->
        <c:forEach var="quiz" items="${quizList}">
            <tr>
                <td>${quiz.name}</td>
                <td>${quiz.id}</td>
                <td><a href="questions?quiz_id=${quiz.id}" class="btn">get questions</a></td>
                <td><a href="quizRun?quiz_id=${quiz.id}" class="btn btn-success"><i class="icon-play"></i>
                    start quiz</a></td>
                <td>
                    <form action="quizzes/delete" method="post">
                        <input type="hidden" name="subject_id" value="${param.subject_id}">
                        <button name="quizId" value="${quiz.id}" class="btn btn-danger"><i
                                class="icon-remove"></i> delete
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/includes/footer.jsp" %>
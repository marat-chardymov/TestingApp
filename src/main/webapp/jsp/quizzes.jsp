<%@ include file="/includes/header.jsp" %>


<div class="container" align="center">
    <c:if test="${user.role.roleName ne 'student'}">
        <form action="quizzes/add" method="post">
            <input type="hidden" name="subjectId" value="${param.subject_id}">
            <fmt:message key="quizzes.quizNameHolder" var="quizNameHolder"/>
            <input type="text" class="input-large" name="quizName" placeholder="${quizNameHolder}">
            <button type="submit" class="btn btn-info" id="add">
                <i class="icon-plus"></i> <fmt:message key="buttons.add"/>
            </button>
        </form>
    </c:if>
    <table class="table table-hover">
        <!--Here should be jstl forEach-->
        <c:forEach var="quiz" items="${quizList}">
            <tr>
                <td>${quiz.name}</td>
                <td><a href="quizRun?quiz_id=${quiz.id}" class="btn btn-success"><i class="icon-play"></i> <fmt:message key="buttons.startQuiz"/></a>
                </td>
                <c:if test="${user.role.roleName ne 'student'}">
                    <td><a href="quizzes/edit?quiz_id=${quiz.id}" class="btn"><i class="icon-pencil"></i> <fmt:message key="buttons.edit"/></a></td>
                    <td>
                        <form action="quizzes/delete" method="post">
                            <input type="hidden" name="subject_id" value="${param.subject_id}">
                            <button name="quizId" value="${quiz.id}" class="btn btn-danger"><i
                                    class="icon-remove"></i> <fmt:message key="buttons.delete"/>
                            </button>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/includes/footer.jsp" %>
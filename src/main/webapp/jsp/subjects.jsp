<%@ include file="/includes/header.jsp" %>

<div class="container" align="center">
    <c:if test="${user.role.roleName ne 'student'}">
        <form action="subjects/add" method="post">
            <fmt:message key="subjects.subjectNameHolder" var="subjectNameHolder"/>
            <input type="text" class="input-large" name="subjectName" placeholder="${subjectNameHolder}">
            <button type="submit" class="btn btn-info" id="add">
                <i class="icon-plus"></i><fmt:message key="buttons.add"/>
            </button>
        </form>
    </c:if>
    <div class="container" align="center">
        <table class="table table-hover" style="width: 500px;">
            <c:forEach var="subject" items="${subjectList}">
                <tr>
                    <td>${subject.name}</td>
                    <td><a href="quizzes?subject_id=${subject.id}" class="btn"><fmt:message key="buttons.getQuizzes"/></a></td>
                    <c:if test="${user.role.roleName ne 'student'}">
                        <td>
                            <form action="subjects/delete" method="post">
                                <button name="subjectId" value="${subject.id}" class="btn btn-danger"><i
                                        class="icon-remove"></i> <fmt:message key="buttons.delete"/>
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="/includes/footer.jsp" %>
<%@ include file="/includes/header.jsp" %>


<div class="container" align="center">
    <table class="table table-hover" style="width:800px">
        <tr>
            <th><fmt:message key="results.username"/></th>
            <th><fmt:message key="results.quiz"/></th>
            <th><fmt:message key="results.scores"/></th>
            <th><fmt:message key="results.date"/></th>
        </tr>
        <c:forEach var="quizResult" items="${quizResultList}">

            <tr>
                <td>${quizResult.userName}</td>
                <td>${quizResult.quizName}</td>
                <td>${quizResult.scores}</td>
                <td><fmt:formatDate value="${quizResult.date}" pattern="dd-MM-yyyy" /></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@ include file="/includes/footer.jsp" %>
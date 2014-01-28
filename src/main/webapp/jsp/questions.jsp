<%@ include file="/includes/header.jsp" %>

<div class="container">
    <div class="container" align="center">
        <table class="table table-hover" style="width: 500px;">
            <c:forEach var="question" items="${questionList}">
                <tr>
                    <td>${question.content}</td>
                    <td>${question.id}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<%@ include file="/includes/footer.jsp" %>
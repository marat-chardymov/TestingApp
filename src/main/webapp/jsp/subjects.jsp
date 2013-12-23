<jsp:include page="${pageContext.request.contextPath}/includes/header.jsp"/>

<div class="container well">
    Here should be subjects list
    <ul>
        <c:forEach items="${subjectList}" var="subject">
            <li>${subject.name}</li>
        </c:forEach>
    </ul>
</div>
<%@ include file="/includes/footer.jsp" %>
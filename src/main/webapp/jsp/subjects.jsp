<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:include page="../includes/header.jsp"/>
Here should be subjects list
<ul>
    <c:forEach items="${subjectList }" var="subject">
        <li><c:out value="${subject.name }"/></li>
    </c:forEach>
</ul>
</body>
</html>
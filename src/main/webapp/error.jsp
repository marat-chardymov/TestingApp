<%@ include file="includes/tagLibs.jsp" %>
<%-- above we include all necessary tag librarise --%>
<html>
<head>
    <title><fmt:message key="errorPage.title"/></title>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/css/404.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="hero-unit">
        <h1>
            <fmt:message key="errorPage.header"/>
        </h1>

        <p>${errorMessage}</p>
        <p>

            <fmt:message key="errorPage.content"/>
        </p>

        <p>
            <a href="<%=request.getContextPath()%>/jsp/home" class="btn btn-info btn-large"><fmt:message key="errorPage.backToHome"/> »</a>
        </p>
    </div>
    <hr>
    <footer>
        <p>
            © Company 2013
        </p>
    </footer>
</div>
</body>
</html>
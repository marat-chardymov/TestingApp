<%@ include file="tagLibs.jsp" %>
<%-- above we include all necessary tag librarise --%>
<! DOCTYPE HTML>
<html lang="${language}">
<head>
    <title>Quiz app</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
</head>
<body>
<div class="container" id="header">
    <div id="loginStatus">
        <div id="loggedIn"><fmt:message key="header.loginAs"/> <span class="label label-info">${user.name} ${user.surname}</span></div>
        <a href="/logout" id="logout"><fmt:message key="header.logout"/> </a>
    </div>
    <div class="navbar">
        <div class="navbar-inner">
            <a class="brand">Testing App</a>
            <ul class="nav">
                <li <%if (request.getServletPath().contains("jsp/home.jsp")) {%>
                        class="active" <%}%> >
                    <a href="../jsp/home.jsp"><fmt:message key="header.navbar.home"/></a>
                </li>
                <li <%if (request.getServletPath().contains("jsp/subjects.jsp")) {%>
                        class="active" <%}%> >
                    <a href="/jsp/subjects"><fmt:message key="header.navbar.subjects"/></a>
                </li>
            </ul>
        </div>
    </div>
</div>

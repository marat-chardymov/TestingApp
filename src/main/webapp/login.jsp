<%@ include file="includes/tagLibs.jsp" %>
<%-- above we include all necessary tag librarise --%>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.forms"/>
<!DOCTYPE HTML>
<html lang="${language}">
<head>
    <link href="<%=request.getContextPath()%>/css/bootstrap.min.css" rel="stylesheet"/>
    <title>login page</title>
</head>
<body>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
</form>
<div class="container" style="margin-top: 120px;">
    <div align=center class="row">
        <div class="span4" style="float: none;">
            <div class="well">
                <legend><fmt:message key="login.plsSignIn"/></legend>
                <form method="POST"
                      action="<%=request.getContextPath()%>/login"
                      accept-charset="UTF-8">
                    <fmt:message key="login.usrholder" var="usrholder"/>
                    <input name="username" class="span3" placeholder="${usrholder}" type="text">
                    <fmt:message key="login.passholder" var="passholder"/>
                    <input name="password" class="span3" placeholder="${passholder}" type="password">
                    <button class="btn-info btn" type="submit"><fmt:message key="login.signInBtn"/></button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>

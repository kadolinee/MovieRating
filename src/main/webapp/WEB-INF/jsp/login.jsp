<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
<title>Login</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:600,300,400|Muli:300|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="resources/css/standardize.css">
    <link rel="stylesheet" href="resources/css/login-style.css">
</head>
<body >
<jsp:include page="_header.jsp"></jsp:include>
<div class="login clearfix">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <c:if test="${errorEnter eq 'true'}">
            <p class="error" name="error_message"><fmt:message key="errorMes1"/> </p>
        </c:if>
        <c:if test="${accessError eq 'true'}">
            <p class="error" name="error_message"><fmt:message key="errorMes2"/> </p>
        </c:if>
        <p class="h1"><fmt:message key="enterSite"/></p>
        <p class="h2 h2-1"><fmt:message key="USERNAME"/>:</p>
        <div class="element_login"></div>
        <input class="_input _input-1" type="text" name="username" required>
        <p class="h2 h2-2"><fmt:message key="PASSWORD"/>:</p>
        <input class="_input _input-2" type="password" name="pwd" required>
        <button class="_button" type="submit"><fmt:message key="LOGIN"/></button>
    </form>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>
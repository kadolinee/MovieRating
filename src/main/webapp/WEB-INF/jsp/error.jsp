<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/standardize.css">
    <link rel="stylesheet" href="/resources/css/error-style.css">
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600|Oswald:400" rel="stylesheet" type="text/css">
</head>
<body>
<jsp:include page="_header.jsp"/>
<div class="error">
    <p class="h3 h3-1">${pageContext.errorData.throwable}<br></p>
    <p class="h2 h2-1"><fmt:message key="exception"/>:<br></p>
    <p class="h3 h3-2">${pageContext.errorData.statusCode}<br></p>
    <p class="h2 h2-2"><fmt:message key="statusCode"/>:<br></p>
    <div class="h3 h3-3">
        <p>${pageContext.errorData.servletName}<br></p>

    </div>
    <p class="h2 h2-3"><fmt:message key="servletTitle"/>:<br></p>
    <p class="h2 h2-4"><fmt:message key="requestFrom"/> ${pageContext.errorData.requestURI} <fmt:message key="isFailed"/></p>
    <div class="element_error"></div>
</div>
<jsp:include page="_footer.jsp"/>
</body>
</html>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="languag" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Info</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:600,300,400|Muli:300|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/resources/css/standardize.css">
    <link rel="stylesheet" href="/resources/css/info-style.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="info">
    <c:if test="${language ne 'en'}">
        <p class="h3">Неизвестная ошибка. Попробуйте позже.<br></p>
    </c:if>
    <c:if test="${language eq 'en'}">
        <p class="h3">Unknown error. Try later.<br></p>
    </c:if>
    <p class="h2"><fmt:message key="ops"/></p>
    <div class="element_info"></div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>

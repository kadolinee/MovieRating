<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<!DOCTYPE html>
<html>
<head>
    <title>User account</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:600,300|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="resources/css/standardize.css">
    <link rel="stylesheet" href="resources/css/useraccount-style.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="useraccount">
    <p class="h2 h2-1"><fmt:formatDate value="${user.dateCreate}"/></p>
    <p class="h2 h2-2">${user.rating}</p>
    <p class="h2 h2-3">${user.mail}</p>
    <p class="h2 h2-4">${user.name}</p>
    <p class="h2 h2-5"><fmt:message key="DATACREATE"/>:</p>
    <p class="h2 h2-6"><fmt:message key="RATING"/>:</p>
    <p class="h1"><fmt:message key="userAccount"/></p>
    <p class="h2 h2-7"><fmt:message key="MAIL"/>:</p>
    <p class="h2 h2-8"><fmt:message key="USERNAME"/>:</p>
    <div class="background"></div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>

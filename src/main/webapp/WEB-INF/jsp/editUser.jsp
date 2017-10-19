<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Edit form</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:600,300|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="resources/css/standardize.css">
    <link rel="stylesheet" href="resources/css/editrating-style.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="editrating">
    <p class="h2 h2-4"><fmt:message key="RATING"/>:</p>
    <p class="h1"><fmt:message key="editRating"/></p>
    <p class="h2 h2-6"><fmt:message key="USERNAME"/>:</p>
    <p class="h2 h2-7"><fmt:message key="ID"/>:</p>
    <p class="h2 h2-2">${user.name}</p>
    <p class="h2 h2-3">${user.id}</p>
    <form action="controller">
        <input type="hidden" name="command" value="editUser"/>
        <input class="_input" type="text" name="rating" value="${user.rating}" required>
        <input type="hidden" name="id" value="${user.id}">
        <button class="_button"><fmt:message key="SUBMIT"/></button>
    </form>
    <div class="background"></div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>

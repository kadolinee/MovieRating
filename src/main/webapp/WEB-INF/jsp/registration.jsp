<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Registration</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:600,300,400|Muli:300|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="resources/css/standardize.css">
    <link rel="stylesheet" href="resources/css/signup-style.css">
    <script type="text/javascript" src="<c:url value="/resources/js/mailValidation.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/loginValidation.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/passwordValidation.js"/>"></script>
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="regist">
    <p class="h1"><fmt:message key="registrationOnSite"/></p>
    <p class="error" id="error"></p>
    <form name="errorForm">
        <input type="hidden" name="errorMail" value="<fmt:message key="errorMes3"/>">
        <input type="hidden" name="errorLogin" value="<fmt:message key="errorMes4"/>">
        <input type="hidden" name="errorPwd" value="<fmt:message key="errorMes5"/>">
    </form>
    <form name="registform" action="controller">
        <input type="hidden" name="command" value="registration"/>
        <p class="h2 h2-1"><fmt:message key="CONFPASSWORD"/>:</p>
        <p class="h2 h2-2"><fmt:message key="MAIL"/>:</p>
        <p class="h2 h2-3"><fmt:message key="PASSWORD"/>:</p>
        <p class="h2 h2-4"><fmt:message key="USERNAME"/>:</p>
        <input class="_input _input-4" type="text" name="username" onblur="checkExistLogin()" title="<fmt:message key="titleLogin"/>" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{5,20}$" required>
        <input class="_input _input-3" type="text" name="mail" title="" oninput="checkExistMail()" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required>
        <input class="_input _input-1" type="password" name="pwd" id="pwd" title="<fmt:message key="titlePassword"/>" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required>
        <input class="_input _input-2" type="password" name="confpwd" title="<fmt:message key="titleConfPassword"/>" oninput="checkPassword(this)" required>
        <p class="agreement"><fmt:message key="agreement"/></p>
        <button class="_button" type="submit" id="sbm"><fmt:message key="SIGNUP"/></button>
    </form>
    <div class="element_login"></div>
</div>
<jsp:include page="_footer.jsp"></jsp:include>
</body>



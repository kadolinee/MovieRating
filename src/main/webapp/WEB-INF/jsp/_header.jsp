<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html lang="${language}">
<head>
    <title>header</title>
</head>

<div class="header clearfix">
    <form action="controller">
        <input type="hidden" name="command" value="searchMovie"/>
        <input class="search" name="search" placeholder="<fmt:message key="search"/>.." type="text" >
    </form>
    <a href="controller?command=goToHome"><p class="logo">KRATING</p></a>
    <div class="element_header"></div>
    <form method="post">
        <select class="_select _select-1" id="language" name="language" onchange="submit()" >
            <option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
            <option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
        </select>
    </form>
    <c:if test="${user eq null}">
        <form action="controller">
            <input type="hidden" name="command" value="goToRegistration"/>
            <button class="_button _button-1" type="submit"><fmt:message key="SIGNUP"/></button>
        </form>
        <form action="controller">
            <input type="hidden" name="command" value="goToLogin"/>
            <button class="_button _button-2" type="submit"><fmt:message key="LOGIN"/></button>
        </form>
    </c:if>
    <c:if test="${user.roleId eq 1}">
        <form action="controller">
            <input type="hidden" name="command" value="goToAddMovie"/>
            <button class="_button _button-3" type="submit"><fmt:message key="ADDMOVIE"/></button>
        </form>
        <form action="controller">
            <input type="hidden" name="command" value="viewUsers"/>
            <button class="_button _button-4" type="submit"><fmt:message key="VIEWUSERS"/></button>
        </form>
    </c:if>
    <c:if test="${user.roleId > 0}">
        <form action="controller">
            <input type="hidden" name="command" value="goToAccount"/>
            <button class="_button _button-2" type="submit"><fmt:message key="PROFILE"/></button>
        </form>
        <form action="controller">
            <input type="hidden" name="command" value="logout"/>
            <button class="_button _button-1" type="submit"><fmt:message key="LOGOUT"/></button>
        </form>
    </c:if>
</div>
</html>

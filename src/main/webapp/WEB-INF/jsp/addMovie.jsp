<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>Movie Form</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:600,300|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="resources/css/standardize.css">
    <link rel="stylesheet" href="resources/css/addmoviepage-style.css">
</head>
<body>
<jsp:include page="_header.jsp"></jsp:include>
<div class="add_movie clearfix">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="addMovie"/>
        <input class="_input _input-1" type="text" name="duration" required>
        <input class="_input _input-13" type="text" name="image" required>
        <input class="_input _input-2" type="text" name="year" required>
        <input class="_input _input-3" type="text" name="awards_en" required>
        <input class="_input _input-4" type="text" name="cast_en" required>
        <textarea class="_textarea _textarea-1" name="title_en" required></textarea>
        <input class="_input _input-5" type="text" name="country_en" required>
        <input class="_input _input-6" type="text" name="genre_en" required>
        <input class="_input _input-7" type="text" name="name_en" required>
        <input class="_input _input-8" type="text" name="awards_ru" required>
        <input class="_input _input-9" type="text" name="cast_ru" required>
        <textarea class="_textarea _textarea-2" name="title_ru" required></textarea>
        <input class="_input _input-10" type="text" name="country_ru" required>
        <input class="_input _input-11" type="text" name="genre_ru" required>
        <input class="_input _input-12" type="text" name="name_ru" required>
        <div class="element_add_movie"></div>
        <p class="h2 h2-1"><fmt:message key="MOVIENAME"/>:</p>
        <p class="h2 h2-2"><fmt:message key="GENRE"/>:</p>
        <p class="h2 h2-3"><fmt:message key="COUNTRY"/>:</p>
        <p class="h2 h2-4"><fmt:message key="TITLE"/>:</p>
        <p class="h2 h2-5"><fmt:message key="CAST"/>:</p>
        <p class="h2 h2-6"><fmt:message key="AWARDS"/>:</p>
        <p class="h2 h2-7"><fmt:message key="YEAR"/>:</p>
        <p class="h2 h2-8"><fmt:message key="DURATION"/>:</p>
        <p class="h2 h2-11"><fmt:message key="IMAGE"/>:</p>
        <label class="checkbox-label clearfix">
            <span class="point-text"><fmt:message key="TVSERIAL"/>:</span>
            <input class="checkbox" type="checkbox" name="tvSerial" value="true">
        </label>
        <button class="_button _button-5" type="submit"><fmt:message key="SUBMIT"/></button>
    </form>
</div>
<p class="h2 h2-9"><fmt:message key="RU"/></p>
<p class="h2 h2-10"><fmt:message key="EN"/></p>
<jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>

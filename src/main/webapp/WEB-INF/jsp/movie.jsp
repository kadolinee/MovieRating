<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://localhost:8080/jsp/tlds/mytags" prefix="mytags"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="languag" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />

<html>
<head>
    <title>Movie</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,400,600|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="resources/css/standardize.css">
    <link rel="stylesheet" href="resources/css/movie-style.css">
</head>
<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <div class="movie clearfix">
        <div class="element_movie"></div>
        <c:if test="${isRated eq false and user ne null}">
            <c:forEach var = "i" begin = "1" end = "10">
                <a href="controller?command=doRating&valuation=${i}&movieId=${movie.id}&numberOfRatings=${numberOfRatings}&rating=${rating}">
                <img class="imager image-${i}" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQSf9aCTkqijiEKptMVpCXoakttkkxojNu2mB5AcM7Ix_Chl8G9">
                </a>
            </c:forEach>
            <p class="h2 h2-22">1</p>
            <p class="h2 h2-23">2</p>
            <p class="h2 h2-24">3</p>
            <p class="h2 h2-25">4</p>
            <p class="h2 h2-26">5</p>
            <p class="h2 h2-27">6</p>
            <p class="h2 h2-28">7</p>
            <p class="h2 h2-29">8</p>
            <p class="h2 h2-30">9</p>
            <p class="h2 h2-31">10</p>

        </c:if>
        <c:if test="${user ne null}">
            <form action="controller">
                <input type="hidden" name="command" value="makeReview"/>
                <textarea class="_textarea" name="review"></textarea>
                <input type="hidden" name="movieId" value="${movie.id}">
                <button class="_button" type="submit"><fmt:message key="leaveReview"/></button>
            </form>
        </c:if>
        <c:if test="${language ne 'en'}">
            <p class="h2 h2-7">${movie.awards_ru}</p>
            <p class="h2 h2-8">${movie.cast_ru}</p>
            <p class="h2 h2-9">${movie.country_ru}</p>
            <p class="h2 h2-10">${movie.genre_ru}</p>
            <p class="h1">${movie.name_ru}</p>
            <div class="h2 h2-12">
                <p>${movie.title_ru}</p>
            </div>
        </c:if>
        <c:if test="${language eq 'en'}">
            <p class="h1">${movie.name_en}</p>
            <p class="h2 h2-7">${movie.awards_en}</p>
            <p class="h2 h2-8">${movie.cast_en}</p>
            <p class="h2 h2-9">${movie.country_en}</p>
            <p class="h2 h2-10">${movie.genre_en}</p>
            <div class="h2 h2-12"><p>${movie.title_en}</p></div>
        </c:if>
        <p class="h2 h2-5">${movie.duration}</p>
        <p class="h2 h2-11">${movie.year}</p>
        <c:choose>
            <c:when test="${movie.tvSerial eq true}">
                <p class="h2 h2-6"><fmt:message key="yes"/></p>
            </c:when>
            <c:otherwise>
                <p class="h2 h2-6"><fmt:message key="no"/></p>
            </c:otherwise>
        </c:choose>
        <div class="comments"></div>
        <div class="container_all_com">
        <c:forEach items="${userActionList}" var="userAction">
            <div class="container_comment container_comment-2">
                <p class="comment comment-2">${userAction.review}</p>
                <p class="h2 h2-2"><fmt:formatDate value="${userAction.dateReview}"/></p>
            </div>
        </c:forEach>
        </div>
        <c:if test="${isRated eq true}">
            <p class="h2 h2-4"><fmt:message key="rating"/>:</p>
            <p class="h2 h2-3"><mytags:formatNumber format="0.00" number="${rating}"/></p>
            <p class="h2 h2-21">${numberOfRatings}</p>
        </c:if>
        <p class="h2 h2-13"><fmt:message key="comments"/>:</p>
        <p class="h2 h2-14"><fmt:message key="duration"/>:</p>
        <p class="h2 h2-15"><fmt:message key="tvSerial"/>:</p>
        <p class="h2 h2-16"><fmt:message key="awards"/>:</p>
        <p class="h2 h2-17"><fmt:message key="cast"/>:</p>
        <p class="h2 h2-18"><fmt:message key="country"/>:</p>
        <p class="h2 h2-19"><fmt:message key="genre"/>:</p>
        <p class="h2 h2-20"><fmt:message key="year"/>:</p>
        <img class="image image-11" src=${movie.image}>
    </div>
    <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>

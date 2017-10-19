<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html>
<head>
    <title>View users</title>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:600,300,400|Oswald:400" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="resources/css/standardize.css">
    <link rel="stylesheet" href="resources/css/viewuserspage-style.css">
</head>
<body>
    <jsp:include page="_header.jsp"></jsp:include>
    <div class="view_users">
        <div class="background"></div>
        <p class="h1"><fmt:message key="viewUs"/></p>
        <div class="user_table">
            <table>
                <tr>
                    <th><fmt:message key="ID"/></th>
                    <th><fmt:message key="userName"/></th>
                    <th><fmt:message key="mail"/></th>
                    <th><fmt:message key="rating"/></th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.mail}</td>
                        <td>${user.rating}</td>
                        <td><a href="controller?command=goToEditUser&id=${user.id}&username=${user.name}&rating=${user.rating}"><button class="_button_table"><fmt:message key="EDITRATING"/></button></a></td>
                        <td><a href="controller?command=banUser&id=${user.id}&rating=${user.rating}"><button class="_button_table"><fmt:message key="BAN"/></button></a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <jsp:include page="_footer.jsp"></jsp:include>
</body>
</html>


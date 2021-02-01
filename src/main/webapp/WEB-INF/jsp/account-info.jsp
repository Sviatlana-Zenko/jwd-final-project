<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="jwdTags" prefix="ct" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Account info</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="account-info-container">
    <form class="info">
        <p><fmt:message key="label.about.account"/></p>
        <ul>
            <li><fmt:message key="label.firstname"/>: ${userFullInfo.firstName}</li>
            <li><fmt:message key="label.lastname"/>: ${userFullInfo.lastName}</li>
            <li><fmt:message key="label.nickname"/>: ${userFullInfo.nickname}</li>
            <li><fmt:message key="label.birthdate"/>: <ct:dateTag date="${userFullInfo.dateOfBirth}"/></li>
            <li><fmt:message key="label.email"/>: ${userFullInfo.email}</li>
            <li><fmt:message key="label.status"/>${userFullInfo.status}</li>
            <li id="user-genres"><fmt:message key="label.user.genres"/>
                <c:forEach var="genre" items="${userFullInfo.favouriteGenres}">
                    ${genre};
                </c:forEach>
            </li>
        </ul>
    </form>
    <div class="account-actions">
        <a href="home?command=edit-account-form">
            <button class="w3-bar-item w3-button w3-teal">
                <fmt:message key="button.edit.account"/>
            </button>
        </a>
        <a href="home?command=delete-account">
            <button class="w3-bar-item w3-button w3-teal">
                <fmt:message key="button.delete.account"/>
            </button>
        </a>
    </div>
</div>
</body>
</html>

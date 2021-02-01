<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Find user page</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="user-operation-btn.jsp"/>
<div class="user-search">
    <form action="home?command=find-user&param=${param.param}" method="POST">
    <c:choose>
        <c:when test="${param.param eq 'id'}">
            <p>
                <fmt:message key="label.enter.id"/>: <input name="id-to-find" type="text" class="input-field"/>
            </p>
            <a href="home?command=find-user&param=${param.param}">
                <button class="w3-bar-item w3-button w3-teal" type="submit">
                    <fmt:message key="button.find"/>
                </button>
            </a>
        </c:when>
        <c:when test="${param.param eq 'nickname'}">
            <p>
                <fmt:message key="label.enter.nickname"/>: <input name="nickname-to-find" type="text" class="input-field"/>
            </p>
            <a href="home?command=find-user&param=${param.param}">
                <button class="w3-bar-item w3-button w3-teal" type="submit">
                    <fmt:message key="button.find"/>
                </button>
            </a>
        </c:when>
    </c:choose>
    </form>
</div>
</body>
</html>

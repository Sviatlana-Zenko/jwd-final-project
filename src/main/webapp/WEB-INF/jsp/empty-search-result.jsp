<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Empty search result</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="empty-search">
    <div class="search-img">
        <i class="fas fa-search-minus fa-6x"></i>
    </div>
    <div class="search-result">
        <p class="description"><fmt:message key="label.no.results"/></p>
        <p>
            <fmt:message key="label.result.one"/> <i style="font-size: 21px">"${sessionScope.search}"</i> <fmt:message key="label.result.two"/><br>
            <fmt:message key="label.result.three"/>
        </p>
    </div>
</div>
</body>
</html>

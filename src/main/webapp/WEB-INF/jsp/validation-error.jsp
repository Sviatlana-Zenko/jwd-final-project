<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Validation-error</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="creation-errors">
    <p>
        <fmt:message key="label.wrong.object"/>
        <fmt:message key="label.error.reasons"/>
    </p>
    <ul>
        <c:forEach var="error" items="${sessionScope.errors}">
            <li>${error};</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>

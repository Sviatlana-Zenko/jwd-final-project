<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>User operations buttons</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="user-operations">
    <a href="home?command=users">
        <button class="w3-bar-item w3-button w3-teal">
            <fmt:message key="button.show.all"/>
        </button>
    </a>
    <a href="home?command=user-search&param=id">
        <button class="w3-bar-item w3-button w3-teal">
            <fmt:message key="button.find.by.id"/>
        </button>
    </a>
    <a href="home?command=user-search&param=nickname">
        <button class="w3-bar-item w3-button w3-teal">
            <fmt:message key="button.find.by.nickname"/>
        </button>
    </a>
</div>
</body>
</html>

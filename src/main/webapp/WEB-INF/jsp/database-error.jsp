<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>New quote form</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<div class="db-error">
    <div id="exclamation-mark">
        <i class="fas fa-exclamation-circle fa-3x"></i>
    </div>
    <p>
        <fmt:message key="label.db.error"/>
    </p>
    <div id="back-btn">
        <a href="home?command=main-page">
            <button class="w3-bar-item w3-button w3-black">
                <fmt:message key="button.to.main"/>
            </button>
        </a>
    </div>
</div>
</body>
</html>

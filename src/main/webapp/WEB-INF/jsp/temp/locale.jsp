<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Locale</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/style.css"/>">
    <script src="<c:url value="../../../js/script.js"/>" defer></script>
</head>
<body>
<button class="w3-button w3-black w3-hover-teal"><fmt:message key="button.languages"/></button>
<div class="w3-dropdown-content w3-bar-block w3-card-4 w3-black">
<%--    <a href="?sessionLocale=en" class="w3-bar-item w3-hover-teal"><fmt:message key="label.lang.en"/></a>--%>
<%--    <a href="?sessionLocale=ru" class="w3-bar-item w3-hover-teal"><fmt:message key="label.lang.ru"/></a>--%>
<%--    <a href="?sessionLocale=by" class="w3-bar-item w3-hover-teal"><fmt:message key="label.lang.by"/></a>--%>

        <a href="?sessionLocale=en" class="w3-bar-item w3-hover-teal"><fmt:message key="label.lang.en"/></a>
        <a href="?sessionLocale=ru" class="w3-bar-item w3-hover-teal"><fmt:message key="label.lang.ru"/></a>
        <a href="?sessionLocale=by" class="w3-bar-item w3-hover-teal"><fmt:message key="label.lang.by"/></a>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Film Rating</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style><%@include file="/css/style.css"%></style>
</head>
<body>

<%--<div class="col-10">--%>
<%--    <c:import url="header.jsp"/>--%>
<%--</div>--%>

Welcome to home page
${sessionScope.lang}

<a href="home?command=show">
    <button><jsp:forward page="/home?command=main-page"></jsp:forward></button>
</a>
<a href="home?command=no-show">
    <button><fmt:message key="button.noshow"/></button>
</a>

<%--<a href="?sessionLocale=en"><fmt:message key="label.lang.en"/></a>--%>
<%--<a href="?sessionLocale=ru"><fmt:message key="label.lang.ru"/></a>--%>
<%--<a href="?sessionLocale=by"><fmt:message key="label.lang.by"/></a>--%>

</body>
</html>

<%--<a href="home?command=show">--%>
<%--    <button>To show</button>--%>
<%--</a>--%>
<%--<a href="home?command=no-show">--%>
<%--    <button>To no-show</button>--%>
<%--</a>--%>

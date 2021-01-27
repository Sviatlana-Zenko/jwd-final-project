<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Title</title>
<%--    <style><%@include file="../../css/style.css"%></style>--%>
</head>
<body>
You are on show-page

<fmt:message key="label.tvseries"/>

<a href="home?command=next">
    <button><fmt:message key="button.go"/></button>
</a>

<%--<c:forEach var="product" items="${requestScope.allShow}">--%>
<%--    <table>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th colspan="2">${product.title}</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <th id="">Type:</th>--%>
<%--            <th>${product.type}</th>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <th>Description:</th>--%>
<%--            <th>${product.description}</th>--%>
<%--        </tr>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--</c:forEach>--%>
</body>
</html>

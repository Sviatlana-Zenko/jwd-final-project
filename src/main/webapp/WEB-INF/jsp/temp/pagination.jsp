<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Pagination</title>
</head>
<body>
<div class="pagination">
    <c:set var="first" value="1"/>
    <c:set var="last" value="${requestScope.pages}"/>
    <c:if test="${param.search != null}">
        <c:set var="search" value="&search=${param.search}"/>
    </c:if>
    <c:choose>
        <c:when test="${requestScope.pages > 10}">
            <c:choose>
                <c:when test="${param.page-1 > 1}">
                    <a href="home?command=${param.command}${search}&page=${param.page-1}" class="w3-bar-item w3-button w3-hover-teal"><</a>
                </c:when>
                <c:when test="${param.page-1 <= 1}">
                    <a href="home?command=${param.command}${search}&page=${1}" class="w3-bar-item w3-button w3-hover-teal"><</a>
                </c:when>
            </c:choose>
            <c:set var="last" value="5"/>
            <c:if test="${param.page > last}">
                <c:set var="first" value="${param.page - 2}"/>
                <c:set var="last" value="${param.page + 2}"/>
                <c:if test="${param.page + 1 >= requestScope.pages}">
                    <c:set var="first" value="${requestScope.pages - 4}"/>
                    <c:set var="last" value="${requestScope.pages}"/>
                </c:if>
            </c:if>
            <c:set var="number" value="${first}"/>
            <c:forEach begin="${first}" end="${last}" varStatus="loop">
                <a href="home?command=${param.command}${search}&page=${number}" class="w3-bar-item w3-button w3-hover-teal">
                        ${number}
                </a>
                <c:set var="number" value="${number + 1}"/>
            </c:forEach>
            <c:choose>
                <c:when test="${param.page+1 < requestScope.pages}">
                    <a href="home?command=${param.command}${search}&page=${param.page+1}" class="w3-bar-item w3-button w3-hover-teal">></a>
                </c:when>
                <c:when test="${param.page+1 >= requestScope.pages}">
                    <a href="home?command=${param.command}${search}&page=${requestScope.pages}" class="w3-bar-item w3-button w3-hover-teal">></a>
                </c:when>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:set var="number" value="${first}"/>
            <c:forEach begin="${first}" end="${last}" varStatus="loop">
                <a href="home?command=${param.command}${search}&page=${number}" class="w3-bar-item w3-button w3-hover-teal">${number}</a>
                <c:set var="number" value="${number + 1}"/>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

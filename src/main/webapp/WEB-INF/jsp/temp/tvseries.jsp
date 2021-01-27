<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>TV Series</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user.role != null}">
        <c:import url="user-header.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="main-header.jsp"/>
    </c:otherwise>
</c:choose>
<c:set var="start" value="1"/>
<c:if test="${param.page != null}">
    <c:set var="start" value="${param.page}"/>
</c:if>
<div class="movies">
    <c:forEach var="movie" items="${requestScope.movies}">
        <table class="product-view" style="border-bottom: 3px solid teal;">
            <tbody>
            <tr>
                <th style="width: 120px; vertical-align: top; horiz-align: center">
                    <img src="${movie.posterUrl}">
                </th>
                <th>
                    <div class="product-info">
                        <p>
                            <i class="fas fa-star fa-lg"></i>
                            <span style="font-size: 18px; padding-top: 2px">
                                    ${movie.currentRating}
                            </span>
                        </p>
                        <p class="title">
                            <a href="home?command=tvseries-info&id=${movie.id}">${movie.title}</a>
                        </p>
                        <p>
                            <fmt:message key="label.release.date"/>${movie.releaseDate}
                        </p>
                        <p>
                            <fmt:message key="label.country"/>${movie.country}
                        </p>
                    </div>
                </th>
            </tr>
            </tbody>
        </table>
    </c:forEach>
    <div class="pagination">
        <c:choose>
            <c:when test="${param.page-1 > 1}">
                <a href="home?command=show-tvseries&page=${param.page-1}" class="w3-bar-item w3-button w3-hover-teal"><</a>
            </c:when>
            <c:when test="${param.page-1 <= 1}">
                <a href="home?command=show-tvseries&page=${1}" class="w3-bar-item w3-button w3-hover-teal"><</a>
            </c:when>
        </c:choose>
        <c:set var="first" value="1"/>
        <c:set var="last" value="${requestScope.pages}"/>
        <c:if test="${requestScope.pages > 10}">
            <c:set var="last" value="5"/>
        </c:if>
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
            <a href="home?command=show-tvseries&page=${number}" class="w3-bar-item w3-button w3-hover-teal">
                    ${number}
            </a>
            <c:set var="number" value="${number + 1}"/>
        </c:forEach>
        <c:choose>
            <c:when test="${param.page+1 < requestScope.pages}">
                <a href="home?command=show-tvseries&page=${param.page+1}" class="w3-bar-item w3-button w3-hover-teal">></a>
            </c:when>
            <c:when test="${param.page+1 >= requestScope.pages}">
                <a href="home?command=show-tvseries&page=${requestScope.pages}" class="w3-bar-item w3-button w3-hover-teal">></a>
            </c:when>
        </c:choose>
    </div>
</div>
</body>
</html>


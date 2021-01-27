<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>TV Series info</title>
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
<div class="movie-info">
    <table style="padding-top: 10px; padding-left: 20px">
        <tbody>
        <tr>
            <th style="width: 220px; vertical-align: top;">
                <img src="${requestScope.movie.posterUrl}" style="width: 100%; max-width: 220px">
            </th>
            <th style="padding-right: 20px;">
                <div class="product-info" style="padding-left: 20px">
                    <p>
                        <i class="fas fa-star fa-lg" style="color: #fd4;"></i>
                        <span style="font-size: 18px; padding-top: 2px">
                            ${movie.currentRating}
                        </span>
                    </p>
                    <p class="title">
                        ${requestScope.movie.title}
                    </p>
                    <p>
                        <fmt:message key="label.release.date"/>${requestScope.movie.releaseDate}
                        (
                        <c:choose>
                            <c:when test="${requestScope.movie.isFinished == true}">
                                <fmt:message key="label.finished"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="label.not.finished"/>
                            </c:otherwise>
                        </c:choose>
                        )
                    </p>
                    <p>
                        <fmt:message key="label.country"/>${requestScope.movie.country}
                    </p>
                    <p>
                        <fmt:message key="label.running.time"/>${requestScope.movie.runningTime}
                    </p>
                    <p>
                        <fmt:message key="label.age.rating"/>${requestScope.movie.ageRating}
                    </p>
                    <p>
                        <fmt:message key="label.genres"/>${requestScope.movie.genres}
                    </p>
                    <p>
                        <fmt:message key="label.starring"/>${requestScope.movie.starring}
                    </p>
                    <p>
                        <fmt:message key="label.seasons"/>${requestScope.movie.numberOfSeasons}
                    </p>
                    <p>
                        <fmt:message key="label.episodes"/>${requestScope.movie.numberOfEpisodes}
                    </p>
                    <p>
                        <fmt:message key="label.description"/>${requestScope.movie.description}
                    </p>
                </div>
            </th>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>


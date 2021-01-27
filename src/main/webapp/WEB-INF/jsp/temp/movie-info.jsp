<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="jwdTags" prefix="ct" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Movie info</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>
<div class="movie-info">
    <table style="padding-top: 10px; padding-left: 20px">
        <tbody>
        <tr>
            <th style="width: 220px; vertical-align: top;">
                <img src="${requestScope.movie.posterUrl}" style="width: 100%; max-width: 220px">
                <div>
                    <a href="home?command=show-reviews&id=${requestScope.movie.id}">
                        <button class="w3-bar-item w3-button w3-teal">
                            <fmt:message key="button.all.reviews"/>
                        </button>
                    </a>
                    <br>
                    <br>
                    <c:choose>
                        <c:when test="${sessionScope.user.reviewedProducts.contains(requestScope.movie.id)}">
                            <button class="w3-bar-item w3-button w3-teal" disabled="disabled">
                                <fmt:message key="button.review.title"/>
                            </button>
                        </c:when>
                        <c:otherwise>
                            <a href="home?command=review-form&id=${requestScope.movie.id}">
                                <button class="w3-bar-item w3-button w3-teal">
                                    <fmt:message key="button.review.title"/>
                                </button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
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

<%--                    <tags:movieInfo movie="${requestScope.movie}"/>--%>

<%--                    <ct:showMovieInfoTag movie="${requestScope.movie}" label="label"/>--%>
                    <p>
                        <fmt:message key="label.release.date"/>${requestScope.movie.releaseDate}
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
                        <fmt:message key="label.directed.by"/>${requestScope.movie.directedBy}
                    </p>
                    <p>
                        <fmt:message key="label.produced.by"/>${requestScope.movie.producedBy}
                    </p>
                    <p>
                        <fmt:message key="label.starring"/>${requestScope.movie.starring}
                    </p>
                    <p>
                        <fmt:message key="label.budget"/>${requestScope.movie.budget}
                    </p>
                    <p>
                        <fmt:message key="label.box.office"/>${requestScope.movie.boxOffice}
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
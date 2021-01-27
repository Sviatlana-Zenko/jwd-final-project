<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Movies</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>
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
                            <a href="home?command=movie-info&id=${movie.id}">${movie.title}</a>
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
    <c:if test="${requestScope.pages > 1}">
        <c:import url="pagination.jsp"/>
    </c:if>
</div>
</body>
</html>


<%--<section class="product-view">--%>
<%--    <c:forEach var="movie" items="${requestScope.movies}">--%>
<%--        <table class="outer">--%>
<%--            <tbody>--%>
<%--            <tr>--%>
<%--                <th>--%>
<%--                    <img src="${movie.posterUrl}" style="width:100%;max-width:100px">--%>
<%--                    <a href="home?command=show-reviews&prodid=${movie.id}">reviews</a>--%>
<%--                </th>--%>
<%--                <th>--%>
<%--                    <div class="product-info">--%>
<%--                        <div>--%>
<%--                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1i7SH8ZILLrUNZQ8SSePrrQH6KYCKM4PB"/>">--%>
<%--                                ${movie.currentRating}--%>
<%--                            <a href="home?command=review-form&prodid=${movie.id}">review this title</a>--%>
<%--                            <table class="inner">--%>
<%--                                <tbody>--%>
<%--                                <tr>--%>
<%--                                    <th>release date:</th>--%>
<%--                                    <th>${movie.releaseDate}</th>--%>
<%--                                </tr>--%>
<%--                                <tr>--%>
<%--                                    <th>title:</th>--%>
<%--                                    <th>${movie.title}</th>--%>
<%--                                </tr>--%>
<%--                                <tr>--%>
<%--                                    <th>country:</th>--%>
<%--                                    <th>${movie.country}</th>--%>
<%--                                </tr>--%>
<%--                                <tr>--%>
<%--                                    <th>running time:</th>--%>
<%--                                    <th>${movie.runningTime}</th>--%>
<%--                                </tr>--%>
<%--                                </tbody>--%>
<%--                            </table>--%>
<%--                            <span class="more-product-info">--%>
<%--                                <table class="inner">--%>
<%--                                <tbody>--%>
<%--                                    <tr>--%>
<%--                                        <th>description:</th>--%>
<%--                                        <th>${movie.description}</th>--%>
<%--                                    </tr>--%>
<%--                                    <tr>--%>
<%--                                        <th>starring:</th>--%>
<%--                                        <th>${movie.starring}</th>--%>
<%--                                    </tr>--%>
<%--                            </table>--%>
<%--                            </span>--%>
<%--                            <button class="w3-bar-item w3-button w3-teal more-button">More</button>--%>
<%--                        </div>--%>
<%--                        <script>--%>
<%--                            $(document).ready(function () {--%>
<%--                                $(".more-button").click(function () {--%>
<%--                                    $(this).prev().toggle();--%>
<%--                                })--%>
<%--                            })--%>
<%--                        </script>--%>
<%--                    </div>--%>
<%--                </th>--%>
<%--            </tr>--%>
<%--            </tbody>--%>
<%--        </table>--%>
<%--    </c:forEach>--%>
<%--</section>--%>
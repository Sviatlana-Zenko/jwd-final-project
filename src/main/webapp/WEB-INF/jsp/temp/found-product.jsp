<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>
<table style="width: 650px; margin-left: auto; margin-right: auto; border-bottom: 3px solid teal">
    <tbody>
    <tr>
        <th>
            <a href="home?command=#">
                <button class="w3-bar-item w3-button w3-teal">
                    find by title
                </button>
            </a>
            <a href="home?command=#">
                <button class="w3-bar-item w3-button w3-teal">
                    create new movie
                </button>
            </a>
            <a href="home?command=#">
                <button class="w3-bar-item w3-button w3-teal">
                    create new tv series
                </button>
            </a>
        </th>
    </tr>
    </tbody>
</table>
<c:forEach var="product" items="${requestScope.products}">
<div class="movie-info">
    <table style="padding-top: 10px; padding-left: 20px">
        <tbody>
        <tr>
            <th style="width: 220px; vertical-align: top;">
                <img src="${product.posterUrl}" style="width: 100%; max-width: 220px">
                <div>
                    <a href="home?command=#">
                        <button class="w3-bar-item w3-button w3-teal">
                            edit
                        </button>
                    </a>
                    <br>
                    <br>
                    <a href="home?command=#">
                        <button class="w3-bar-item w3-button w3-teal">
                            delete
                        </button>
                    </a>
                </div>
            </th>
            <th style="padding-right: 20px;">
                <div class="product-info" style="padding-left: 20px">
                    <p>
                        <i class="fas fa-star fa-lg" style="color: #fd4;"></i>
                        <span style="font-size: 18px; padding-top: 2px">
                            ${product.currentRating}
                        </span>
                    </p>
                    <p class="title">
                        ${product.title}
                    </p>

                    <%--                    <tags:movieInfo movie="${requestScope.movie}"/>--%>

                    <%--                    <ct:showMovieInfoTag movie="${requestScope.movie}" label="label"/>--%>
                    <p>
                        <fmt:message key="label.release.date"/>${product.releaseDate}
                    </p>
                    <p>
                        <fmt:message key="label.country"/>${product.country}
                    </p>
                    <p>
                        <fmt:message key="label.running.time"/>${product.runningTime}
                    </p>
                    <p>
                        <fmt:message key="label.age.rating"/>${product.ageRating}
                    </p>
                    <p>
                        <fmt:message key="label.genres"/>${product.genres}
                    </p>
                    <p>
                        <fmt:message key="label.directed.by"/>${product.directedBy}
                    </p>
                    <p>
                        <fmt:message key="label.produced.by"/>${product.producedBy}
                    </p>
                    <p>
                        <fmt:message key="label.starring"/>${product.starring}
                    </p>
                    <p>
                        <fmt:message key="label.budget"/>${product.budget}
                    </p>
                    <p>
                        <fmt:message key="label.box.office"/>${product.boxOffice}
                    </p>
                    <p>
                        <fmt:message key="label.description"/>${product.description}
                    </p>
                </div>
            </th>
        </tr>
        </tbody>
    </table>
</div>
</c:forEach>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="jwdTags" prefix="ct" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Movie info</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="prod-info">
    <table>
        <tbody>
        <td>
            <th id="poster">
                <img src="${requestScope.product.posterUrl}">
                <div>
                    <a href="home?command=show-reviews&id=${requestScope.product.id}">
                        <button class="w3-bar-item w3-button w3-teal" style="width: 150px; font-weight: normal">
                            <fmt:message key="button.all.reviews"/>
                        </button>
                    </a>
                    <br>
                    <br>
                    <c:choose>
                        <c:when test="${sessionScope.user.reviewedProducts.contains(requestScope.product.id) ||
                                        sessionScope.user.isBanned eq true}">
                            <button class="w3-bar-item w3-button w3-teal" disabled="disabled"
                                    style="width: 150px; font-weight: normal">
                                <fmt:message key="button.review.title"/>
                            </button>
                        </c:when>
                        <c:otherwise>
                            <a href="home?command=review-form&id=${requestScope.product.id}">
                                <button class="w3-bar-item w3-button w3-teal" style="width: 150px; font-weight: normal">
                                    <fmt:message key="button.review.title"/>
                                </button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </th>
            <th style="padding-right: 20px;">
                <p style="color: teal; font-size: 18px">
                    <i class="fas fa-star fa-sm" style="color: orange"></i>
                    ${product.currentRating} ${requestScope.product.title}
                </p>
                <table class="info-inner">
                    <tbody>
                    <tr>
                        <th class="field-name"><fmt:message key="label.release.date"/>: </th>
                        <th><ct:dateTag date="${requestScope.product.releaseDate}"/></th>
                    </tr>
                    <tr>
                        <th class="field-name"><fmt:message key="label.country"/>: </th>
                        <th>${requestScope.product.country}</th>
                    </tr>
                    <tr>
                        <th class="field-name"><fmt:message key="label.running.time"/>: </th>
                        <th>${requestScope.product.runningTime}<fmt:message key="label.min"/></th>
                    </tr>
                    <tr>
                        <th class="field-name"><fmt:message key="label.age.rating"/>: </th>
                        <th>${requestScope.product.ageRating}+</th>
                    </tr>
                    <tr>
                        <th class="field-name"><fmt:message key="label.genres"/>: </th>
                        <th id="product-genres">
                            <c:forEach var="genre" items="${requestScope.product.genres}">
                                ${genre};
                            </c:forEach>
                        </th>
                    </tr>
                    <tr>
                        <th class="field-name"><fmt:message key="label.starring"/>: </th>
                        <th>${requestScope.product.starring}</th>
                    </tr>
                    <c:choose>
                        <c:when test="${requestScope.product.type eq 'MOVIE'}">
                            <tr>
                                <th class="field-name"><fmt:message key="label.directed.by"/>: </th>
                                <th>${requestScope.product.directedBy}</th>
                            </tr>
                            <tr>
                                <th class="field-name"><fmt:message key="label.produced.by"/>: </th>
                                <th>${requestScope.product.producedBy}</th>
                            </tr>
                            <tr>
                                <th class="field-name"><fmt:message key="label.budget"/>: </th>
                                <th><ct:splitterTag amount="${requestScope.product.budget}"/>$</th>
                            </tr>
                            <tr>
                                <th class="field-name"><fmt:message key="label.box.office"/>: </th>
                                <th><ct:splitterTag amount="${requestScope.product.boxOffice}"/>$</th>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <th class="field-name"><fmt:message key="label.seasons"/>: </th>
                                <th>
                                    ${requestScope.product.numberOfSeasons}
                                    <c:choose>
                                        <c:when test="${requestScope.product.isFinished == true}">
                                            <fmt:message key="label.finished"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message key="label.not.finished"/>
                                        </c:otherwise>
                                    </c:choose>
                                </th>
                            </tr>
                            <tr>
                                <th class="field-name"><fmt:message key="label.episodes"/>: </th>
                                <th>${requestScope.product.numberOfEpisodes}</th>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <th class="field-name"><fmt:message key="label.description"/>: </th>
                    </tr>
                    </tbody>
                </table>
                <p id="story-line">${requestScope.product.description}</p>
            </th>
        </td>
        </tbody>
    </table>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Reviews</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<section class="review-container">
    <c:if test="${requestScope.reviews.size() == 0}">
        <p id="none-reviews"><fmt:message key="label.no.reviews"/></p>
    </c:if>
    <c:forEach var="review" items="${requestScope.reviews}">
        <div class="review-info">
            <p id="rew-ttl">
                <b>${review.cinemaProductMark}/10 "${review.reviewSummary}"
            </p>
            <p id="rew-nick">${review.userNickname}</p>
            <c:choose>
                <c:when test="${review.hasSpoilers == false}">
                    <p>${review.reviewText}</p>
                </c:when>
                <c:otherwise>
                    <span id="spoiler"><fmt:message key="label.spoilers"/></span>
                    <button class="read"><i class="fas fa-chevron-down"></i></button>
                    <br>
                    <span class="more">
                        <p>${review.reviewText}</p>
                    </span>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.user.ratedReviews.containsKey(review.id)}">
                    <c:choose>
                        <c:when test="${sessionScope.user.isBanned eq true}">
                            <div>
                                <button class="w3-bar-item w3-button w3-teal thumbs" disabled="disabled">
                                    <i class="far fa-thumbs-up"></i>${review.reviewPositiveMarks}
                                </button>
                                <button class="w3-bar-item w3-button w3-teal thumbs" disabled="disabled">
                                    <i class="far fa-thumbs-down"></i>${review.reviewNegativeMarks}
                                </button>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq true}">
                                <div>
                                    <button class="w3-bar-item w3-button w3-teal thumbs">
                                        <i class="far fa-thumbs-up"></i>${review.reviewPositiveMarks}
                                    </button>
                                    <button class="w3-bar-item w3-button w3-teal thumbs" disabled="disabled">
                                        <i class="far fa-thumbs-down"></i>${review.reviewNegativeMarks}
                                    </button>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq false}">
                                <div>
                                    <button class="w3-bar-item w3-button w3-teal thumbs" disabled="disabled">
                                        <i class="far fa-thumbs-up"></i>${review.reviewPositiveMarks}
                                    </button>
                                    <button class="w3-bar-item w3-button w3-teal thumbs">
                                        <i class="far fa-thumbs-down"></i>${review.reviewNegativeMarks}
                                    </button>
                                </div>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <div>
                        <a href="home?command=edit-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewPositiveMarks}&neg=${review.reviewNegativeMarks}&plus=t">
                            <button class="w3-bar-item w3-button w3-teal thumbs">
                                <i class="far fa-thumbs-up"></i>${review.reviewPositiveMarks}
                            </button>
                        </a>
                        <a href="home?command=edit-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewPositiveMarks}&neg=${review.reviewNegativeMarks}&plus=f">
                            <button class="w3-bar-item w3-button w3-teal thumbs">
                                <i class="far fa-thumbs-down"></i>${review.reviewNegativeMarks}
                            </button>
                        </a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>
</section>
<script>
    $(document).ready(function () {
        $(".read").click(function () {
            $(this).next().next().toggle();
        });
    });
</script>
</body>
</html>

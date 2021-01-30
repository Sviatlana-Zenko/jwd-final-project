<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>User reviews</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="user-reviews-container">
    <table class="user-reviews">
        <tbody>
        <c:forEach var="review" items="${requestScope.userReviews}">
            <tr>
                <td>
                    <p id="mark-ttl">
                        <b>${review.cinemaProductMark}/10 ${review.productTitle}</b>
                    </p>
                    <p id="rew-summary">
                        "${review.reviewSummary}"
                    </p>
                    <p>${review.reviewText}</p>
                    <p id="rew-marks">
                        ${review.reviewPositiveMarks} <fmt:message key="label.helpful"/><br>
                        ${review.reviewNegativeMarks} <fmt:message key="label.not.helpful"/><br>
                    </p>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

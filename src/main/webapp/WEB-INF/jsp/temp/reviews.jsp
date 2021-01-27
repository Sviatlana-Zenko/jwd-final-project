<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Reviews</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>

<table class="outer">
    <tbody>
    <tr>
        <th>
            <p>${movie.title}</p>
            <img src="${movie.posterUrl}">
        </th>
        <th>
            <c:forEach var="review" items="${requestScope.reviews}">
                <section>
                    <div class="review-info">
                        <table class="inner-review">
                            <tbody>
                                <tr>
                                    <th>${review.cinemaProductMark}/10</th>
                                </tr>
                                <tr>
                                    <th>${review.userNickname}</th>
                                </tr>
                                <tr>
                                    <th>${review.reviewSummary}</th>
                                </tr>
                            </tbody>
                        </table>
                        <c:choose>
                            <c:when test="${review.hasSpoilers == true}">
                                <p style="color: firebrick">Warning: Spoilers</p>
                                <span class="review-txt" id="review-txt">
                                    <table class="inner-review">
                                        <tbody>
                                           <tr>
                                                <th>${review.reviewText}</th>
                                            </tr>
                                        </tbody>
                                    </table>
                                 </span>
                                <button class="w3-bar-item w3-button w3-teal" id="show">Show</button>
                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                                <script type="text/javascript">
                                    $(document).ready(function(){
                                        $("#show").click(function(){
                                            if ($(this).html() == 'Show') {
                                                $('#review-txt').show();
                                                $(this).html('Hide');
                                            } else {
                                                $('#review-txt').hide();
                                                $(this).html('Show');
                                            }
                                        });
                                    });
                                </script>
                            </c:when>
                            <c:otherwise>
                                <table>
                                    <tbody>
                                        <tr>
                                            <th>${review.reviewText}</th>
                                        </tr>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                        <div>
                            <c:choose>
                                <c:when test="${sessionScope.user.ratedReviews.containsKey(review.id)}">
                                    <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq true}">
                                        <button class="w3-bar-item w3-button w3-teal">
                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}
                                        </button>
                                        <button class="w3-bar-item w3-button w3-teal" disabled="disabled">
                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}
                                        </button>
                                    </c:if>
                                    <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq false}">
                                        <button class="w3-bar-item w3-button w3-teal" disabled="disabled">
                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}
                                        </button>
                                        <button class="w3-bar-item w3-button w3-teal">
                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}
                                        </button>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <a href="home?command=update-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewPositiveMarks}&neg=${review.reviewNegativeMarks}&first=true">
                                        <button class="w3-bar-item w3-button w3-teal">
                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}
                                        </button>
                                    </a>
                                    <a href="home?command=update-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewNegativeMarks}&neg=${review.reviewNegativeMarks}&first=false">
                                        <button class="w3-bar-item w3-button w3-teal">
                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}
                                        </button>
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </section>
            </c:forEach>
        </th>
    </tr>
    </tbody>
</table>
</body>
</html>




<%--                    <c:choose>--%>
<%--                        <c:when test="${sessionScope.user.ratedReviews.containsKey(review.id)}">--%>
<%--                            <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq true}">--%>
<%--                                <button class="w3-bar-item w3-button w3-teal">--%>
<%--                                    <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}--%>
<%--                                </button>--%>
<%--                                <button class="w3-bar-item w3-button w3-teal" disabled="disabled">--%>
<%--                                    <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}--%>
<%--                                </button>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq false}">--%>
<%--                                <button class="w3-bar-item w3-button w3-teal" disabled="disabled">--%>
<%--                                    <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}--%>
<%--                                </button>--%>
<%--                                <button class="w3-bar-item w3-button w3-teal">--%>
<%--                                    <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}--%>
<%--                                </button>--%>
<%--                            </c:if>--%>
<%--                        </c:when>--%>
<%--                        <c:otherwise>--%>
<%--                            <a href="home?command=update-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewPositiveMarks}&neg=${review.reviewNegativeMarks}&first=true">--%>
<%--                                <button class="w3-bar-item w3-button w3-teal">--%>
<%--                                    <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}--%>
<%--                                </button>--%>
<%--                            </a>--%>
<%--                            <a href="home?command=update-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewNegativeMarks}&neg=${review.reviewNegativeMarks}&first=false">--%>
<%--                                <button class="w3-bar-item w3-button w3-teal">--%>
<%--                                    <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}--%>
<%--                                </button>--%>
<%--                            </a>--%>
<%--                        </c:otherwise>--%>
<%--                    </c:choose>--%>






<%--<table class="inner-review">--%>
    <%--                            <tbody>--%>
    <%--                            <tr>--%>
    <%--                                <th>${review.cinemaProductMark}/10</th>--%>
    <%--                            </tr>--%>
    <%--                            <tr>--%>
    <%--                                <th>${review.userNickname}</th>--%>
    <%--                            </tr>--%>
    <%--                            <tr>--%>
    <%--                                <th>${review.reviewSummary}</th>--%>
    <%--                            </tr>--%>
    <%--                            <tr>--%>
    <%--                                <th>${review.reviewText}</th>--%>
    <%--                            </tr>--%>
    <%--                            <th>--%>
    <%--                                <c:choose>--%>
    <%--                                    <c:when test="${sessionScope.user.ratedReviews.containsKey(review.id)}">--%>
    <%--                                        <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq true}">--%>
    <%--                                            <button class="w3-bar-item w3-button w3-teal">--%>
    <%--                                                <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}--%>
    <%--                                            </button>--%>
    <%--                                            <button class="w3-bar-item w3-button w3-teal" disabled="disabled">--%>
    <%--                                                <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}--%>
    <%--                                            </button>--%>
    <%--                                        </c:if>--%>
    <%--                                        <c:if test="${sessionScope.user.ratedReviews.get(review.id) eq false}">--%>
    <%--                                            <button class="w3-bar-item w3-button w3-teal" disabled="disabled">--%>
    <%--                                                <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}--%>
    <%--                                            </button>--%>
    <%--                                            <button class="w3-bar-item w3-button w3-teal">--%>
    <%--                                                <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}--%>
    <%--                                            </button>--%>
    <%--                                        </c:if>--%>
    <%--                                    </c:when>--%>
    <%--                                    <c:otherwise>--%>
    <%--                                        <a href="home?command=update-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewPositiveMarks}&neg=${review.reviewNegativeMarks}&first=true">--%>
    <%--                                            <button class="w3-bar-item w3-button w3-teal">--%>
    <%--                                                <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">${review.reviewPositiveMarks}--%>
    <%--                                            </button>--%>
    <%--                                        </a>--%>
    <%--                                        <a href="home?command=update-marks&id=${review.cinemaProductId}&rewid=${review.id}&pos=${review.reviewNegativeMarks}&neg=${review.reviewNegativeMarks}&first=false">--%>
    <%--                                            <button class="w3-bar-item w3-button w3-teal">--%>
    <%--                                                <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">${review.reviewNegativeMarks}--%>
    <%--                                            </button>--%>
    <%--                                        </a>--%>
    <%--                                    </c:otherwise>--%>
    <%--                                </c:choose>--%>
    <%--                            </th>--%>
    <%--                            </tbody>--%>
    <%--                        </table>


















    <%--&lt;%&ndash;                                        <form class="positive-marks-form" id="positive-marks-form" action="home?command=update-marks" method="POST">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <input id="pos" name="pos" type="text">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </form>&ndash;%&gt;--%>
<%--<form class="positive-marks-form" id="positive-marks-form" action="home?command=update-marks" method="POST">--%>
<%--    &lt;%&ndash;                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">&ndash;%&gt;--%>
<%--    <button class="w3-bar-item w3-button w3-teal more-button" name="pos" id="pos" type="submit" value="${review.reviewPositiveMarks}">--%>
<%--        ${review.reviewPositiveMarks}--%>
<%--    </button>--%>
<%--    &lt;%&ndash;                                            <input id="pos" value="${review.reviewPositiveMarks}" name="pos" type="submit">&ndash;%&gt;--%>
<%--</form>--%>
<%--&lt;%&ndash;                                        <button onclick="plus()" class="w3-bar-item w3-button w3-teal more-button" name="positive-marks" id="positive-marks">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1Y_cUo40fxL7pQgxbIdyHUrehoF5ZjKJU"/>">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                ${review.reviewPositiveMarks}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </button>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <button class="w3-bar-item w3-button w3-teal more-button" name="negative-marks">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <img src="<c:url value="https://drive.google.com/uc?export=download&id=1VntEgYRM6eGY36aEhX8BHRxwDdP1h4r-"/>">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                                ${review.reviewNegativeMarks}&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </button>&ndash;%&gt;--%>

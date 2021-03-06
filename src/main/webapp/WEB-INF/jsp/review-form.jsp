<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>New review form</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="<c:url value="../../js/review-check.js"/>" defer></script>
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<c:import url="header.jsp"/>
<div class="container">
    <p>
        <fmt:message key="label.new.review"/>
    </p>
    <form class="review-form" id="review-form" action="home?command=create-review&id=${param.id}" method="POST">
        <div class="star-widget">
            <input type="radio" name="rate-10" id="rate-10">
            <label for="rate-10" class="fas fa-star"></label>
            <input type="radio" name="rate-9" id="rate-9">
            <label for="rate-9" class="fas fa-star"></label>
            <input type="radio" name="rate-8" id="rate-8">
            <label for="rate-8" class="fas fa-star"></label>
            <input type="radio" name="rate-7" id="rate-7">
            <label for="rate-7" class="fas fa-star"></label>
            <input type="radio" name="rate-6" id="rate-6">
            <label for="rate-6" class="fas fa-star"></label>
            <input type="radio" name="rate-5" id="rate-5">
            <label for="rate-5" class="fas fa-star"></label>
            <input type="radio" name="rate-4" id="rate-4">
            <label for="rate-4" class="fas fa-star"></label>
            <input type="radio" name="rate-3" id="rate-3">
            <label for="rate-3" class="fas fa-star"></label>
            <input type="radio" name="rate-2" id="rate-2">
            <label for="rate-2" class="fas fa-star"></label>
            <input type="radio" name="rate-1" id="rate-1">
            <label for="rate-1" class="fas fa-star"></label>
        </div>
        <div>
            <input id="summary" name="summary" type="text" class="input-field"
                   placeholder="<fmt:message key="placeholder.summary"/>"/>
        </div>
        <div id="text-div">
            <textarea name="rev-text" id="rev-text" maxlength="1500"
                      style="resize: none; width: 350px; height: 200px"></textarea>
        </div>
        <div id="spoiler-check">
            <input type="checkbox" id="spoilers" name="spoilers">
            <label for="spoilers" style="color: white"><fmt:message key="label.spoilers"/></label>
        </div>
        <div style="width: 350px; text-align: center">
            <a href="home?command=create-review&id=${param.id}">
                <button class="w3-bar-item w3-button w3-teal" type="submit" style="width: 105px;">
                    <fmt:message key="button.save"/>
                </button>
            </a>
        </div>
    </form>
</div>
</body>
</html>

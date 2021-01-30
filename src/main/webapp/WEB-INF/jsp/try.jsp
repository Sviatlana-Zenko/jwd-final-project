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
<div class="container">
    <div class="star-widget">
        <input type="radio" name="rate 10" id="rate-10">
        <label for="rate-10" class="fas fa-star"></label>
        <input type="radio" name="rate 9" id="rate-9">
        <label for="rate-9" class="fas fa-star"></label>
        <input type="radio" name="rate 8" id="rate-8">
        <label for="rate-8" class="fas fa-star"></label>
        <input type="radio" name="rate 7" id="rate-7">
        <label for="rate-7" class="fas fa-star"></label>
        <input type="radio" name="rate 6" id="rate-6">
        <label for="rate-6" class="fas fa-star"></label>
        <input type="radio" name="rate 5" id="rate-5">
        <label for="rate-5" class="fas fa-star"></label>
        <input type="radio" name="rate 4" id="rate-4">
        <label for="rate-4" class="fas fa-star"></label>
        <input type="radio" name="rate 3" id="rate-3">
        <label for="rate-3" class="fas fa-star"></label>
        <input type="radio" name="rate 2" id="rate-2">
        <label for="rate-2" class="fas fa-star"></label>
        <input type="radio" name="rate 1" id="rate-1">
        <label for="rate-1" class="fas fa-star"></label>
    </div>
</div>
</body>
</html>

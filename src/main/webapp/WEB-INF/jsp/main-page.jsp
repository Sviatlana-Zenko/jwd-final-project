<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<c:import url="header.jsp"/>
<div class="main">
    <div class="recommendation">
        <p id="to-rate">
            <fmt:message key="label.recommendation"/>
        </p>
        <table>
            <tbody>
            <tr>
                <c:forEach var="product" items="${requestScope.recommendations}">
                    <th>
                        <img src="${product.posterUrl}"/><br>
                        <p>
                            <i class="fas fa-star fa-sm"></i>
                                ${product.currentRating}
                            <a href="home?command=product-info&id=${product.id}">${product.title}</a>
                        </p>
                    </th>
                </c:forEach>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="random-quote">
        <p>
            <fmt:message key="label.random.quote"/>
        </p>
        <table>
        <tbody>
        <tr>
            <th id="q-img">
                <img src="${requestScope.random.posterUrl}">
            </th>
            <th id="q-body">
                <p id="q-txt">
                    <i class="fas fa-quote-right fa-2x"></i><br>
                    ${requestScope.random.quoteText}
                </p>
                <p id="q-title">
                    - ${requestScope.random.productTitle}
                </p>
            </th>
            <th id="q-description">
                <p>
                    <fmt:message key="label.about.random.quote"/>
                </p>
            </th>
        </tr>
        </tbody>
    </table>
    </div>
</div>
</body>
</html>

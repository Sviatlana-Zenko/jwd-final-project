<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="jwdTags" prefix="ct" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Found product</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="product-operation-btn.jsp"/>
<c:choose>
    <c:when test="${requestScope.products == null || requestScope.products.size() == 0}">
        <p id="product-not-found"><fmt:message key="label.product.not.found"/></p>
    </c:when>
    <c:otherwise>
        <div class="products">
            <c:forEach var="product" items="${requestScope.products}">
                <table class="product-view">
                    <tbody>
                    <tr>
                        <th><img src="${product.posterUrl}"></th>
                        <th>
                            <div class="product-info">
                                <p>
                                    <i class="fas fa-star fa-lg"></i>
                                    <span style="font-size: 18px; padding-top: 2px">
                                            ${product.currentRating}
                                    </span>
                                </p>
                                <div class="title">
                                    <a href="home?command=product-info&id=${product.id}">${product.title}</a>
                                </div>
                                <p>
                                    <fmt:message key="label.release.date"/><ct:dateTag date="${product.releaseDate}"/>
                                </p>
                                <div style="text-align: right">
                                    <a href="home?command=delete-product&id=${product.id}">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </div>
                            </div>
                        </th>
                    </tr>
                    </tbody>
                </table>
            </c:forEach>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>

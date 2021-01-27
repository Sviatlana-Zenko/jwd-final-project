<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Products view</title>
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="products">
    <c:forEach var="product" items="${requestScope.products}">
    <table class="product-view">
        <tbody>
        <tr>
            <th>
                <img src="${product.posterUrl}">
            </th>
            <th>
                <div class="product-info">
                    <p>
                        <i class="fas fa-star fa-lg"></i>
                        <span>${product.currentRating}</span>
                    </p>
                    <p class="title">
                        <a href="home?command=tvseries-info&id=${product.id}">${product.title}</a>
                    </p>
                    <p>
                        <fmt:message key="label.release.date"/>${product.releaseDate}
                    </p>
                    <p>
                        <fmt:message key="label.country"/>${product.country}
                    </p>
                </div>
            </th>
        </tr>
        </tbody>
    </table>
    </c:forEach>
</div>
</body>
</html>

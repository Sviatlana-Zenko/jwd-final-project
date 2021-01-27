<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Quotes</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user.role != null}">
        <c:import url="user-header.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="main-header.jsp"/>
    </c:otherwise>
</c:choose>

<c:set var="start" value="1"/>
<c:if test="${param.page != null}">
    <c:set var="start" value="${param.page}"/>
</c:if>

<div class="quotes">
    <c:forEach var="quote" items="${requestScope.quotes}">
        <table class="quote-view" style="border-bottom: 3px solid teal;">
            <tbody>
            <tr>
                <th style="width: 120px; vertical-align: top; horiz-align: center">
                    <img src="${quote.posterUrl}">
                </th>
                <th>
                    <div class="quote-info">
                        <p>
                            <fmt:message key="label.product.title"/>${quote.productTitle}
                        </p>
                        <p>
                            <fmt:message key="label.quote"/>${quote.quoteText}
                        </p>
                        <div>
                            <a href="home?command=edit-quote">
                                <button class="w3-bar-item w3-button w3-teal">
                                    <fmt:message key="button.edit"/>
                                </button>
                            </a>
                            <a href="home?command=delete-account">
                                <button class="w3-bar-item w3-button w3-teal">
                                    <fmt:message key="button.delete"/>
                                </button>
                            </a>
                        </div>
                    </div>
                </th>
            </tr>
            </tbody>
        </table>
    </c:forEach>
    <c:import url="pagination.jsp"/>
</div>
</body>
</html>
</body>
</html>

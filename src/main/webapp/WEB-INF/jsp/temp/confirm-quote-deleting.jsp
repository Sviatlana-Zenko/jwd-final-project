<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Work with quotes</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>
<div class="confirm-quote">
    <p>
        <fmt:message key="label.delete.quote"/>
    </p>
    <a href="home?command=delete-quote&id=${param.id}">
        <button class="w3-bar-item w3-button w3-teal">
            <fmt:message key="button.yes"/>
        </button>
    </a>
    <a href="home?command=quote-operations">
        <button class="w3-bar-item w3-button w3-teal">
            <fmt:message key="button.cancel"/>
        </button>
    </a>
    <div class="confirm-btn">
<%--        <div id="yes-btn">--%>
<%--            <a href="home?command=delete-quote&id=${param.id}">--%>
<%--                <button class="w3-bar-item w3-button w3-teal">--%>
<%--                    <fmt:message key="button.yes"/>--%>
<%--                </button>--%>
<%--            </a>--%>
<%--        </div>--%>
<%--        <div id="cancel-bnt">--%>
<%--            <a href="home?command=quote-operations">--%>
<%--                <button class="w3-bar-item w3-button w3-teal">--%>
<%--                    <fmt:message key="button.cancel"/>--%>
<%--                </button>--%>
<%--            </a>--%>
<%--        </div>--%>
    </div>
</div>
<div class="quote-to-delete">
    <table>
        <tbody>
        <tr>
            <th>
                <img src="${requestScope.quote.posterUrl}" width="140px">
            </th>
            <th>
                <p id="txt">
                    "${requestScope.quote.quoteText}"</p>
                <p id="ttl">
                    <fmt:message key="label.from"/> ${requestScope.quote.productTitle}
                </p>
            </th>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>

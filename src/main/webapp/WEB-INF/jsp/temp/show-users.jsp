<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>
<table style="width: 650px; margin-left: auto; margin-right: auto; border-bottom: 3px solid teal">
    <tbody>
    <tr>
        <th>
            <a href="home?command=show-users">
                <button class="w3-bar-item w3-button w3-teal">
                    <fmt:message key="button.show.all"/>
                </button>
            </a>
            <a href="home?command=user-by-id">
                <button class="w3-bar-item w3-button w3-teal">
                    <fmt:message key="button.find.by.id"/>
                </button>
            </a>
            <a href="home?command=#">
                <button class="w3-bar-item w3-button w3-teal">
                    <fmt:message key="button.find.by.nickname"/>
                </button>
            </a>
        </th>
    </tr>
    </tbody>
</table>
<table style="width: 670px; margin-left: auto; margin-right: auto; border-bottom: 1px solid teal">
    <tbody style="color: white">
    <tr>
        <th>
            id
        </th>
        <th>
            nickname
        </th>
        <th>
            email
        </th>
        <th>
            status
        </th>
        <th>
            options
        </th>
    </tr>
    <c:forEach var="user" items="${requestScope.users}">
        <tr style="height: 50px; font-size: 15px">
            <th>
                ${user.id}
            </th>
            <th>
                ${user.nickname}
            </th>
            <th>
                ${user.email}
            </th>
            <th>
                ${user.status}
            </th>
            <th>
                <c:choose>
                    <c:when test="${user.banned == false}">
                        <a href="home?command=change-block-status&status=block&id=${user.id}">
                            <button class="w3-bar-item w3-button w3-teal">
                                block user
                            </button>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="home?command=change-block-status&status=unblock&id=${user.id}">
                            <button class="w3-bar-item w3-button w3-teal">
                                unblock user
                            </button>
                        </a>
                    </c:otherwise>
                </c:choose>
                <a href="home?command=#">
                    <button class="w3-bar-item w3-button w3-teal">
                        delete
                    </button>
                </a>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>
<c:import url="pagination.jsp"/>
</body>
</html>

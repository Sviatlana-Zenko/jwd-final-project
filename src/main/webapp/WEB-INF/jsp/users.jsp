<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>All users</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="user-operation-btn.jsp"/>
<div>
    <table class="users">
        <tbody style="color: white">
        <tr style="border-bottom: 2px solid teal; padding-top: 10px">
            <th>id</th>
            <th><fmt:message key="label.nickname"/></th>
            <th><fmt:message key="label.email"/></th>
            <th><fmt:message key="label.operations"/></th>
        </tr>
        <c:forEach var="user" items="${requestScope.users}">
            <tr id="user-info" style="height: 50px; font-size: 15px">
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
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Found user</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<c:import url="user-operation-btn.jsp"/>
<c:choose>
    <c:when test="${requestScope.user == null}">
        <p id="user-not-found">
            <fmt:message key="label.user.not.found"/>
        </p>
    </c:when>
    <c:otherwise>
        <div>
            <table class="users">
                <tbody style="color: white">
                <tr id="heading">
                    <th>id</th>
                    <th><fmt:message key="label.nickname"/></th>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.operations"/></th>
                </tr>
                <tr id="user-info" style="height: 50px; font-size: 15px">
                    <th>
                            ${requestScope.user.id}
                    </th>
                    <th style="width: 153px">
                            ${requestScope.user.nickname}
                    </th>
                    <th style="width: 275px">
                            ${requestScope.user.email}
                    </th>
                    <th style="width: 215px">
                        <c:choose>
                            <c:when test="${requestScope.user.banned == false}">
                                <a href="home?command=change-block-status&status=block&id=${requestScope.user.id}">
                                    <button class="w3-bar-item w3-button w3-teal">
                                        <fmt:message key="button.block"/>
                                    </button>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="home?command=change-block-status&status=unblock&id=${requestScope.user.id}">
                                    <button class="w3-bar-item w3-button w3-teal">
                                        <fmt:message key="button.unblock"/>
                                    </button>
                                </a>
                            </c:otherwise>
                        </c:choose>
                        <a href="home?command=delete-user&id=${requestScope.user.id}">
                            <button class="w3-bar-item w3-button w3-teal">
                                <fmt:message key="button.delete"/>
                            </button>
                        </a>
                    </th>
                </tr>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>

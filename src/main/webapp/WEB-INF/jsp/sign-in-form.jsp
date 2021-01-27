<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>

<html>
<link>
    <title>Sign-in form</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="<c:url value="../../js/sign-in-check.js"/>" defer></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<div class="sign-in-container">
    <p class="form-title"><fmt:message key="label.login.form"/></p>
    <c:if test="${sessionScope.failed == true}">
        <div class="sign-in-error">
            <p>
                <fmt:message key="label.signin.error"/>
            </p>
        </div>
    </c:if>
    <form class="sign-in-form" id="sign-in-form" action="home?command=sign-in" method="POST">
        <div class="input">
            <input id="email" name="email" type="text" class="input-field"
                   placeholder="<fmt:message key="placeholder.email"/>"/>
        </div>
        <div class="input">
            <input id="password" name="password" type="password" class="input-field"
                   placeholder="<fmt:message key="placeholder.password"/>"/>
        </div>
        <a href="home?command=sign-in">
            <button class="w3-bar-item w3-button w3-teal" type="submit" id="login-button">
                <fmt:message key="button.singin"/>
            </button>
        </a>
    </form>
    <div class="form-info">
    <p>
        <fmt:message key="label.click"/>
        <a href="home?command=new-account-form"><fmt:message key="label.here"/></a> <fmt:message key="label.create.account"/>
    </p>
    </div>
</div>
</body>
</html>

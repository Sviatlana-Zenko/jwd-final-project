<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>New account form</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="<c:url value="../../js/new-account-check.js"/>" defer></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<div class="new-account-container">
    <div class="title"><fmt:message key="label.new.account"/></div>
    <div class="sign-up-error">
        <p>
            <c:if test="${sessionScope.emailError == true}">
                <fmt:message key="label.email.error"/>
            </c:if>
        </p>
        <p>
            <c:if test="${sessionScope.nicknameError == true}">
                <fmt:message key="label.nickname.error"/>
            </c:if>
        </p>
    </div>
    <form class="account-form" id="account-form" action="home?command=create-account" method="POST">
        <table class="account-table">
            <tbody>
                <tr>
                    <td>
                        <div class="section">
                            <div>
                                <input id="first-name" name="first-name" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.firstname"/>" maxlength="25"/>
                            </div>
                            <div>
                                <input id="last-name" name="last-name" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.lastname"/>" maxlength="25"/>
                            </div>
                            <div>
                                <input id="nickname" name="nickname" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.nickname"/>" maxlength="25"/>
                            </div>
                            <div>
                                <input id="date-of-birth" name="date-of-birth" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.birthdate"/>"/>
                                <div class="rule"><fmt:message key="rule.date"/></div>
                            </div>
                            <div>
                                <input id="email" name="email" type="text" class="input-field"
                                       placeholder=" <fmt:message key="placeholder.email"/>" maxlength="100"/>
                            </div>
                            <div>
                                <input id="password" name="password" type="password" class="input-field"
                                       placeholder=" <fmt:message key="placeholder.password"/>" maxlength="20"/>
                                <div class="rule">
                                    <fmt:message key="rule.password"/>
                                </div>
                            </div>
                            <div>
                                <input id="confirm-password" name="confirm-password" type="password" class="input-field"
                                       placeholder=" <fmt:message key="placeholder.confirm.password"/>" maxlength="20"/>
                            </div>
                        </div>
                    </td>
                    <td><c:import url="genres.jsp"/></td>
                </tr>
            </tbody>
        </table>
        <div class="create-btn">
            <button class="w3-bar-item w3-button w3-teal w3-mobile" type="submit" id="account-button">
                <fmt:message key="button.create"/>
            </button>
        </div>
    </form>
</div>
</body>
</html>

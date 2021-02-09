<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="jwdTags" prefix="ct" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Edit account form</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="<c:url value="../../js/edit-account-check.js"/>" defer></script>
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<c:import url="header.jsp"/>
<div class="new-account-container">
    <div class="title"><fmt:message key="button.edit.account"/></div>
    <p style="text-align: center; color: white; "><fmt:message key="label.edit.rule"/></p>
    <form class="account-form" id="account-form" action="home?command=edit-account" method="POST">
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
                <fmt:message key="button.edit.account"/>
            </button>
        </div>
    </form>
</div>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>New Account Form</title>
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
                email error
            </c:if>
            <c:if test="${sessionScope.nicknameError == true}">
                nickname error
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
                    <td>
                        <div class="section">Favorite genres:
                            <div class="genres">
                                <input type="checkbox" name="adventure" id="adventure">
                                <label for="adventure"><fmt:message key="genre.adventure"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="anime" id="anime">
                                <label for="anime"><fmt:message key="genre.anime"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="biography" id="biography">
                                <label for="biography"><fmt:message key="genre.biography"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="cartoon" id="cartoon">
                                <label for="cartoon"><fmt:message key="genre.cartoon"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="comedy" id="comedy">
                                <label for="comedy"><fmt:message key="genre.comedy"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="crime" id="crime">
                                <label for="crime"><fmt:message key="genre.crime"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="detective" id="detective">
                                <label for="detective"><fmt:message key="genre.detective"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="drama" id="drama">
                                <label for="drama"><fmt:message key="genre.drama"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="family" id="family">
                                <label for="family"><fmt:message key="genre.family"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="fantasy" id="fantasy">
                                <label for="fantasy"><fmt:message key="genre.fantasy"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="fiction" id="fiction">
                                <label for="fiction"><fmt:message key="genre.fiction"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="historical" id="historical">
                                <label for="historical"><fmt:message key="genre.historical"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="horror" id="horror">
                                <label for="horror"><fmt:message key="genre.horror"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="melodrama" id="melodrama">
                                <label for="melodrama"><fmt:message key="genre.melodrama"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="military" id="military">
                                <label for="military"><fmt:message key="genre.military"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="musical" id="musical">
                                <label for="musical"><fmt:message key="genre.musical"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="sitcom" id="sitcom">
                                <label for="sitcom"><fmt:message key="genre.sitcom"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="sport" id="sport">
                                <label for="sport"><fmt:message key="genre.sport"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="thriller" id="thriller">
                                <label for="thriller"><fmt:message key="genre.thriller"/></label>
                            </div>
                            <div class="genres">
                                <input type="checkbox" name="western" id="western">
                                <label for="western"><fmt:message key="genre.western"/></label>
                            </div>
                        </div>
                    </td>
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

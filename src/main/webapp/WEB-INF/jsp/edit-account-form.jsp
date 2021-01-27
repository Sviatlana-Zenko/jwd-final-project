<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
<%--    <script src="<c:url value="../../../js/script.js"/>" defer></script>--%>
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>

<form class="edit-account" id="edit-account" action="home?command=edit-account&user-id=${sessionScope.user.id}" method="POST">
    <table>
        <tbody>
        <tr>
            <td>
                <div class="section">
                    <div>
                        <input id="first-name" name="first-name" type="text" class="input-field" placeholder="first name" maxlength="20"/>
                    </div>
                    <div>
                        <input id="last-name" name="last-name" type="text" class="input-field" placeholder="last name"/>
                    </div>
                    <div>
                        <input id="nickname" name="nickname" type="text" class="input-field" placeholder="nickname"/>
                    </div>
                    <div>
                        <input id="date-of-birth" name="date-of-birth" type="text" class="input-field" placeholder="date of birth"/>
                        <div class="rule">*format: DD-MM-YYYY</div>
                    </div>

                    <div>
                        <input id="email" name="email" type="text" class="input-field" placeholder="email"/>
                    </div>
                    <div>
                        <input id="password" name="password" type="password" class="input-field" placeholder="password"/>
                        <div class="rule">
                            *length from 8 to 20 characters<br>
                            should include at least 1 uppercase letter,<br>
                            1 lowercase letter and 1 digit
                        </div>
                    </div>
                    <div>
                        <input id="confirm-password" name="confirm-password" type="password" class="input-field" placeholder="confirm password"/>
                    </div>
                </div>
            </td>
            <td>
                <div class="section">Favorite genres:
                    <div class="genres">
                        <input type="checkbox" name="adventure" id="adventure">
                        <label for="adventure">Adventure</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="anime" id="anime">
                        <label for="anime">Anime</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="biography" id="biography">
                        <label for="biography">Biography</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="cartoon" id="cartoon">
                        <label for="cartoon">Cartoon</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="crime" id="crime">
                        <label for="crime">Crime</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="detective" id="detective">
                        <label for="detective">Detective</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="drama" id="drama">
                        <label for="drama">Drama</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="family" id="family">
                        <label for="family">Family</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="fantasy" id="fantasy">
                        <label for="fantasy">Fantasy</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="fiction" id="fiction">
                        <label for="fiction">Fiction</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="historical" id="historical">
                        <label for="historical">Historical</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="horror" id="horror">
                        <label for="horror">Horror</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="melodrama" id="melodrama">
                        <label for="melodrama">Melodrama</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="military" id="military">
                        <label for="military">Military</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="musical" id="musical">
                        <label for="musical">Musical</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="sitcom" id="sitcom">
                        <label for="sitcom">Sitcom</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="sport" id="sport">
                        <label for="sport">Sport</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="thriller" id="thriller">
                        <label for="thriller">Thriller</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="western" id="western">
                        <label for="western">Western</label>
                    </div>
                    <div class="genres">
                        <input type="checkbox" name="comedy" id="comedy">
                        <label for="comedy">Comedy</label>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <a href="home?command=edit-account&user-id=${sessionScope.user.id}">
        <button class="w3-bar-item w3-button w3-teal" type="submit" id="login-button"><fmt:message key="button.singin"/></button>
    </a>
<%--    <button class="w3-bar-item w3-button w3-teal w3-mobile" type="submit" id="account-button"><fmt:message key="button.singin"/></button>--%>
</form>

</body>
</html>

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
    <script src="<c:url value="../../js/new-movie-check.js"/>" defer></script>
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<c:import url="header.jsp"/>
<div class="new-movie-container">
    <div class="title"><fmt:message key="label.new.movie"/></div>
    <form class="movie-form" id="movie-form" action="home?command=create-movie" method="POST">
        <table class="movie-table">
            <tbody>
                <tr>
                    <td>
                        <div class="section">
                            <div>
                                <input id="title" name="title" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.title"/>" maxlength="100"/>
                            </div>
                            <div class="age-select">
                                <select class="age" name="age" id="select-field">
                                    <option value="0" name="0">0+</option>
                                    <option value="6" name="6">6+</option>
                                    <option value="12" name="12">12+</option>
                                    <option value="16" name="16">16+</option>
                                    <option value="18" name="18" selected>18+</option>
                                </select>
                            </div>
                            <div>
                                <input id="release-date" name="release-date" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.release.date"/>" maxlength="10">
                                <div class="rule"><fmt:message key="rule.date"/></div>
                            </div>
                            <div>
                                <input id="time" name="time" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.running.time"/>"/>
                                <div class="rule"><fmt:message key="rule.time"/></div>
                            </div>
                            <div>
                                <input id="country" name="country" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.country"/>" maxlength="50"/>
                            </div>
                            <div>
                                <input id="starring" name="starring" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.starring"/>" maxlength="250"/>
                            </div>
                            <div>
                                <input id="directed-by" name="directed-by" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.directed.by"/>"/>
                            </div>
                            <div>
                                <input id="produced-by" name="produced-by" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.produced.by"/>"/>
                            </div>
                            <div>
                                <input id="budget" name="budget" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.budget"/>"/>
                            </div>
                            <div>
                                <input id="box-office" name="box-office" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.box.office"/>"/>
                            </div>
                            <div class="product-descr">
                                <textarea name="description" id="description" maxlength="1500" placeholder="<fmt:message key="label.description"/>" style="resize: none; width: 300px; height: 200px"></textarea>
                            </div>
                            <div>
                                <input id="poster-url" name="poster-url" type="text" class="input-field"
                                       placeholder=" <fmt:message key="label.poster.url"/>" maxlength="100"/>
                            </div>
                        </div>
                    </td>
                    <td>
                        <c:import url="genres.jsp"/>
                    </td>
                </tr>
            </tbody>
        </table>
        <div class="create-btn">
            <button class="w3-bar-item w3-button w3-teal w3-mobile" type="submit">
                <fmt:message key="button.create"/>
            </button>
        </div>
    </form>
</div>
</body>
</html>

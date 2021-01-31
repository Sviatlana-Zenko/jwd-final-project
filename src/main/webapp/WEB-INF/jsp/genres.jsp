<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Genres</title>
</head>
<body>
<div class="section">
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
</body>
</html>

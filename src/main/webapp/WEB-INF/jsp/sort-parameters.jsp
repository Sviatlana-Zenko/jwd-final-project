<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Parameters for sorting</title>
</head>
<body>
<div class="sort-select">
    <form action="home?command=show-products&type=${param.type}&page=1" method="post">
        <span style="margin-right: 10px"><fmt:message key="label.sort.by"/></span>
        <select class="sort-field" name="field">
            <c:choose>
                <c:when test="${sessionScopefield == null || sessionScope.field eq 'rating'}">
                    <option value="rating" name="rating" selected>
                        <fmt:message key="option.rating"/>
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="rating" name="rating">
                        <fmt:message key="option.rating"/>
                    </option>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.field eq 'release'}">
                    <option value="release" name="release" selected>
                        <fmt:message key="option.date"/>
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="release" name="release">
                        <fmt:message key="option.date"/>
                    </option>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.field eq 'title'}">
                    <option value="title" name="title" selected>
                        <fmt:message key="option.title"/>
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="title" name="title">
                        <fmt:message key="option.title"/>
                    </option>
                </c:otherwise>
            </c:choose>
        </select>
        <select class="sort-dir" name="dir">
            <c:choose>
                <c:when test="${sessionScope.dir == null || sessionScope.dir eq 'descending'}">
                    <option value="descending" name="descending" selected>
                        <fmt:message key="option.desc"/>
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="descending" name="descending">
                        <fmt:message key="option.desc"/>
                    </option>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${sessionScope.dir eq 'ascending'}">
                    <option value="ascending" name="ascending" selected>
                        <fmt:message key="option.asc"/>
                    </option>
                </c:when>
                <c:otherwise>
                    <option value="ascending" name="ascending">
                        <fmt:message key="option.asc"/>
                    </option>
                </c:otherwise>
            </c:choose>
        </select>
        <button class="w3-bar-item w3-button w3-teal" type="submit" id="sort-btn">
            <fmt:message key="button.sort"/>
        </button>
    </form>
</div>
</body>
</html>

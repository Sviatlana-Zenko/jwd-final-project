<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Header</title>
</head>
<body>
<div class="w3-bar w3-black">
    <span class="branding w3-bar-item w3-text-teal">Let's rate it!</span>
    <span class="w3-right">
        <form class="search-form" id="search-form" action="home?command=search" method="POST">
            <a href="home?command=show-products&type=movie" class="w3-bar-item w3-button w3-hover-teal">
                <fmt:message key="label.movies"/>
            </a>
            <a href="home?command=show-products&type=tvseries" class="w3-bar-item w3-button w3-hover-teal">
                <fmt:message key="label.tvseries"/>
            </a>
            <a href="home?command=main-page" class="w3-bar-item w3-button w3-hover-teal">
                <fmt:message key="button.home"/>
            </a>
            <c:if test="${sessionScope.user.role == 'admin'}">
                <div class="w3-dropdown-hover">
                    <button class="w3-button w3-black w3-hover-teal" type="button">
                        <fmt:message key="button.admin.actions"/>
                    </button>
                    <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-black">
                        <a href="home?command=user-operations" class="w3-bar-item w3-button w3-hover-teal">
                            <fmt:message key="button.user.work"/>
                        </a>
                        <a href="home?command=product-operations" class="w3-bar-item w3-button w3-hover-teal">
                            <fmt:message key="button.products.work"/>
                        </a>
                        <a href="home?command=quote-operations" class="w3-bar-item w3-button w3-hover-teal">
                            <fmt:message key="button.quote.work"/>
                        </a>
                    </div>
                </div>
            </c:if>
            <c:if test="${sessionScope.user == null}">
                <a href="home?command=sign-in-form" class="w3-bar-item w3-button w3-hover-teal">
                    <fmt:message key="button.singin"/>
                </a>
            </c:if>
            <c:if test="${sessionScope.user != null}">
                <div class="w3-dropdown-hover">
                    <button class="w3-button w3-black w3-hover-teal" type="button">
                        <fmt:message key="label.hello"/>${sessionScope.user.nickname}
                    </button>
                    <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-black">
                        <a href="home?command=account-info" class="w3-bar-item w3-button w3-hover-teal">
                            <fmt:message key="button.account.info"/>
                        </a>
                        <a href="home?command=show-user-reviews" class="w3-bar-item w3-button w3-hover-teal">
                            <fmt:message key="button.my.reviews"/>
                        </a>
                        <a href="home?command=sign-out" class="w3-bar-item w3-button w3-hover-teal">
                            <fmt:message key="button.signout"/>
                        </a>
                    </div>
                </div>
            </c:if>
            <div class="w3-dropdown-hover">
                <button class="w3-button w3-black w3-hover-teal" type="button">
                    <i class="fas fa-globe fa-lg"></i>
                </button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4 w3-black">
                    <a href="${sessionScope.queryString}sessionLocale=en" class="w3-bar-item w3-button w3-hover-teal">
                        <fmt:message key="label.lang.en"/>
                    </a>
                    <a href="${sessionScope.queryString}sessionLocale=ru" class="w3-bar-item w3-button w3-hover-teal">
                        <fmt:message key="label.lang.ru"/>
                    </a>
                    <a href="${sessionScope.queryString}sessionLocale=by" class="w3-bar-item w3-button w3-hover-teal">
                        <fmt:message key="label.lang.by"/>
                    </a>
                </div>
            </div>
            <input type="text" id="search-request" name="search-request" class="w3-bar-item w3-input"
                   placeholder="<fmt:message key="placeholder.search"/>" maxlength="100"/>
            <a href="home?command=search">
                <button class="w3-bar-item w3-button w3-teal" type="submit" id="go-button">
                    <fmt:message key="button.go"/>
                </button>
            </a>
        </form>
    </span>
</div>
</body>
</html>

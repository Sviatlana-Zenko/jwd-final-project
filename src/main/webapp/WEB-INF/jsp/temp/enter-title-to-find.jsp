<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>
<table style="width: 650px; margin-left: auto; margin-right: auto; border-bottom: 3px solid teal">
    <tbody>
    <tr>
        <th>
            <a href="home?command=#">
                <button class="w3-bar-item w3-button w3-teal">
                    find by title
                </button>
            </a>
            <a href="home?command=#">
                <button class="w3-bar-item w3-button w3-teal">
                    create new movie
                </button>
            </a>
            <a href="home?command=#">
                <button class="w3-bar-item w3-button w3-teal">
                    create new tv series
                </button>
            </a>
        </th>
    </tr>
    </tbody>
</table>
<form class="title-form" id="title-form" action="home?command=find-product" method="POST">
    <div class="input">
        <input id="title-to-find" name="title-to-find" type="text" class="input-field" placeholder="title to find"/>
    </div>
    <a href="home?command=find-product">
        <button class="w3-bar-item w3-button w3-teal" type="submit" id="find-button">
            find
        </button>
    </a>
</form>
</body>
</html>

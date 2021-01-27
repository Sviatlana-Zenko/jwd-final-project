<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Admin actions</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="../header.jsp"/>
<div class="admin-actions">
    <table style="color: white; width: 600px; margin-left: auto; margin-top: auto">
        <tbody>
            <tr>
                <th>
                    Work with quotes:<br>
                    <a href="home?command=edit-quote-form">
                        edit quote
                    </a><br>
                    - ....<br>
                    - ....
                </th>
                <th>
                    Work with users:<br>
                    - ....<br>
                    - ....<br>
                    - ....
                </th>
            </tr>
            <tr>
                <th>
                    Work with movies:<br>
                    - ....<br>
                    - ....<br>
                    - ....
                </th>
                <th>
                    Work with tv series:<br>
                    - ....<br>
                    - ....<br>
                    - ....
                </th>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>

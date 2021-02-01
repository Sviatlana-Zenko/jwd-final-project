<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Quote operations</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body>
<c:import url="header.jsp"/>
<div class="quote-options">
    <select class="select" id="select-field">
        <option value="All" selected><fmt:message key="option.all"/></option>
        <c:forEach var="option" items="${requestScope.options}">
            <option value="${option}">
                ${requestScope.titles.get(option)}
            </option>
        </c:forEach>
    </select>
    <a href="home?command=new-quote-form">
        <i class="fas fa-plus"></i> <fmt:message key="label.add.quote"/>
    </a>
</div>
<div class="quote-view">
    <c:choose>
        <c:when test="${requestScope.options != null}">
            <table id="quote-table">
                <tbody>
                <c:forEach var="option" items="${requestScope.options}">
                    <c:forEach var="quote" items="${requestScope.quotes}">
                        <c:if test="${requestScope.titles.get(option) == quote.productTitle}">
                            <tr position="${option}">
                                <td id="col-one">
                                    <img src="${quote.posterUrl}" width="70px">
                                </td>
                                <td id="col-two">
                                    <p id="txt">
                                        "${quote.quoteText}"
                                    </p>
                                    <p id="ttl">
                                        <fmt:message key="label.from"/> ${quote.productTitle}
                                    </p>
                                </td>
                                <td id="edit-btn">
                                    <a href="home?command=edit-quote-form&id=${quote.id}">
                                        <i class="fas fa-edit"></i>
                                    </a>
                                </td>
                                <td id="delete-btn">
                                    <a href="home?command=confirm-quote-deleting&id=${quote.id}">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p id="no-quotes">
                <fmt:message key="label.no.quotes"/>
            </p>
        </c:otherwise>
    </c:choose>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        var rows = $("table#quote-table tr");
        $("#select-field").on("change", function() {
            var selected = this.value;
            if (selected != "All") {
                rows.filter("[position=" + selected + "]").show();
                rows.not("[position=" + selected + "]").hide();
            } else {
                rows.show();
            }
        });
    });
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>New quote form</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="<c:url value="../../css/app-style.css"/>">
    <script src="<c:url value="../../js/quote-check.js"/>" defer></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://kit.fontawesome.com/93c41a9677.js" crossorigin="anonymous"></script>
</head>
<body style="background: linear-gradient(#0b9e9e, #202121) fixed">
<c:import url="header.jsp"/>
<div class="new-quote-container">
    <p class="form-title"><fmt:message key="label.new.quote"/></p>
    <form class="new-quote-form" id="new-quote-form" action="home?command=create-quote" method="POST">
        <div class="quote-instr">
            <button class="w3-bar-item w3-button w3-teal instr-button" type="button">
                <i class="far fa-question-circle"></i>
            </button>
            <span class="help-info">
                <p><fmt:message key="label.quote.question"/></p>
                <p><fmt:message key="label.create.quote.rule"/></p>
                <p><fmt:message key="label.quote.rules"/></p>
            </span>
            <script>
                $(document).ready(function () {
                    $(".instr-button").click(function () {
                        $(this).next().toggle();
                    })
                })
            </script>
        </div>
        <div class="quote-field">
            <textarea maxlength="100" id="prod-title"
                      name="prod-title" placeholder=" <fmt:message key="label.product.title"/>"></textarea>
        </div>
        <div class="quote-field">
            <textarea maxlength="100" id="quote-txt"
                      name="quote-txt" placeholder=" <fmt:message key="label.quote.text"/>"></textarea>
        </div>
        <div class="quote-field">
            <textarea maxlength="100" id="poster-url"
                      name="poster-url" placeholder=" <fmt:message key="label.poster.url"/>"></textarea>
        </div>
        <div class="save-quote-button">
            <a href="home?command=create-quote">
                <button class="w3-bar-item w3-button w3-teal" type="submit" id="save-button">
                    <fmt:message key="button.create"/>
                </button>
            </a>
        </div>
    </form>
</div>
</body>
</html>

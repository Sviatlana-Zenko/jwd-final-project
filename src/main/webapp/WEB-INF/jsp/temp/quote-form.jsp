<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Work with quotes</title>
</head>
<body>
<form class="quote-form" id="quote-form" action="home?command=create-quote" method="POST">
    <div>
        <input id="product-title" name="product-title" type="text" class="input-field" placeholder="movie/TV series title"/>
    </div>
    <div>
        <input id="quote-text" name="quote-text" type="text" class="input-field" placeholder="quote text"/>
    </div>
    <div>
        <input id="poster-url" name="poster-url" type="text" class="input-field" placeholder="poster url"/>
    </div>
    <a href="home?command=create-quote">
        <button class="w3-bar-item w3-button w3-teal" type="submit" id="save-button"><fmt:message key="button.save"/></button>
    </a>
</form>
</body>
</html>

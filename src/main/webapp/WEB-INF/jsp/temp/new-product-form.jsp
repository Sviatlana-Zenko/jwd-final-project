<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Create new quote</title>
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
            <a href="home?command=enter-title">
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
<form class="movie-form" id="movie-form" action="home?command=create-movie" method="POST">
    <div>
        <input id="product-title" name="product-title" type="text" class="input-field" placeholder="product title"/>
    </div>
    <div>
        <input id="description" name="description" type="text" class="input-field" placeholder="description"/>
    </div>
    <div>
        <input id="release-date" name="release-date" type="text" class="input-field" placeholder="release date"/>
    </div>
    <div>
        <input id="running-time" name="running-time" type="text" class="input-field" placeholder="running time"/>
    </div>
    <div>
        <input id="country" name="country" type="text" class="input-field" placeholder="country"/>
    </div>
    <div>
        <input id="age_rating" name="age_rating" type="text" class="input-field" placeholder="age rating"/>
    </div>
    <div>
        <input id="starring" name="starring" type="text" class="input-field" placeholder="starring"/>
    </div>
    <div>
        <input id="directed_by" name="directed_by" type="text" class="input-field" placeholder="directed by"/>
    </div>
    <div>
        <input id="produced_by" name="produced_by" type="text" class="input-field" placeholder="produced by"/>
    </div>
    <div>
        <input id="budget" name="budget" type="text" class="input-field" placeholder="budget"/>
    </div>
    <div>
        <input id="box_office" name="box_office" type="text" class="input-field" placeholder="box office"/>
    </div>
    <div>
        <input id="poster_url" name="poster_url" type="text" class="input-field" placeholder="poster url"/>
    </div>

    <div class="section">Genres:
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

    <a href="home?command=create-movie">
        <button class="w3-bar-item w3-button w3-teal" type="submit" id="save-button">create</button>
    </a>

<%--    directed_by VARCHAR(100) NOT NULL,--%>
<%--    produced_by VARCHAR(150) NOT NULL,--%>
<%--    budget INT,--%>
<%--    box_office INT,--%>

</form>
</body>
</html>

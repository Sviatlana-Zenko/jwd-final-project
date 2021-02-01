const title = document.getElementById('title');
const releaseDate = document.getElementById('release-date');
const time = document.getElementById('time');
const country = document.getElementById('country');
const starring = document.getElementById('starring');
const directedBy = document.getElementById('directed-by');
const producedBy = document.getElementById('produced-by');
const budget = document.getElementById('budget');
const boxOffice = document.getElementById('box-office');
const description = document.getElementById('description');
const posterUrl = document.getElementById('poster-url');
const movieForm = document.getElementById('movie-form');
const dataRegex = new RegExp('((0[1-9])|([12][0-9])|(3[01]))-((0[1-9])|(1[012]))-[12][01]\\d{2}')
const urlRegex = new RegExp('^https://drive\\.google\\.com/.+');

movieForm.addEventListener('submit', (e) => {
    if (title.value.length == 0) {
        title.style.border = "2px solid red"
        e.preventDefault();
    } else {
        title.style.border = "2px none"
    }

    if (releaseDate.value.length == 0 ||
        !dataRegex.test(releaseDate.value)) {
        releaseDate.style.border = "2px solid red"
        e.preventDefault();
    } else {
        releaseDate.style.border = "2px none"
    }

    if (time.value.length == 0 ||
        isNaN(time.value) ||
        time.value <= 0) {
        time.style.border = "2px solid red"
        e.preventDefault();
    } else {
        time.style.border = "2px none"
    }

    if (country.value.length == 0) {
        country.style.border = "2px solid red"
        e.preventDefault();
    } else {
        country.style.border = "2px none"
    }

    if (starring.value.length == 0) {
        starring.style.border = "2px solid red"
        e.preventDefault();
    } else {
        starring.style.border = "2px none"
    }

    if (directedBy.value.length == 0) {
        directedBy.style.border = "2px solid red"
        e.preventDefault();
    } else {
        directedBy.style.border = "2px none"
    }

    if (producedBy.value.length == 0) {
        producedBy.style.border = "2px solid red"
        e.preventDefault();
    } else {
        producedBy.style.border = "2px none"
    }

    if (budget.value.length == 0 ||
        isNaN(budget.value) ||
        budget.value < 0) {
        budget.style.border = "2px solid red"
        e.preventDefault();
    } else {
        budget.style.border = "2px none"
    }

    if (boxOffice.value.length == 0 ||
        isNaN(boxOffice.value) ||
        boxOffice.value < 0) {
        boxOffice.style.border = "2px solid red"
        e.preventDefault();
    } else {
        boxOffice.style.border = "2px none"
    }

    if (description.value.length == 0) {
        description.style.border = "2px solid red"
        e.preventDefault();
    } else {
        description.style.border = "2px none"
    }

    if (posterUrl.value.length == 0 ||
        posterUrl.value.length != 80 ||
        !urlRegex.test(posterUrl.value)) {
        posterUrl.style.border = "2px solid red";
        e.preventDefault();
    } else {
        posterUrl.style.border = "2px none"
    }
});

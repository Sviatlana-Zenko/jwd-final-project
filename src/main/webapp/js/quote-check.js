const productTitle = document.getElementById('prod-title');
const quoteText = document.getElementById('quote-txt');
const posterUrl = document.getElementById('poster-url');
const quoteForm = document.getElementById('new-quote-form');
const urlRegex = new RegExp('^https://drive\\.google\\.com/.+');

quoteForm.addEventListener('submit', (e) => {
    if (productTitle.value.length === 0) {
        productTitle.style.border = "2px solid red"
        e.preventDefault();
    } else {
        productTitle.style.border = "2px none"
    }

    if (quoteText.value.length === 0) {
        quoteText.style.border = "2px solid red";
        e.preventDefault();
    } else {
        quoteText.style.border = "2px none"
    }

    if (posterUrl.value.length === 0 ||
        posterUrl.value.length != 80 ||
        !urlRegex.test(posterUrl.value)) {
        posterUrl.style.border = "2px solid red";
        e.preventDefault();
    } else {
        posterUrl.style.border = "2px none"
    }

});

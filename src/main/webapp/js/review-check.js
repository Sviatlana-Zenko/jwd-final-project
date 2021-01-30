const reviewText = document.getElementById('rev-text');
const summary = document.getElementById('summary');
const reviewForm = document.getElementById('review-form');

reviewForm .addEventListener('submit', (e) => {
    if (summary.value.length === 0) {
        summary.style.border = "2px solid red"
        e.preventDefault();
    } else {
        summary.style.border = "2px none"
    }

    if (reviewText.value.length === 0) {
        reviewText.style.border = "2px solid red";
        e.preventDefault();
    } else {
        reviewText.style.border = "2px none"
    }
});

const email = document.getElementById('email');
const password = document.getElementById('password');
const signInForm = document.getElementById('sign-in-form');
const emailRegex = new RegExp('^\\w+([-+.\']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$');

signInForm .addEventListener('submit', (e) => {
    if (email.value.length === 0 ||
            !emailRegex.test(email.value)) {
        email.style.border = "2px solid red"
        e.preventDefault();
    } else {
        email.style.border = "2px none"
    }

    if (password.value.length === 0) {
        password.style.border = "2px solid red";
        e.preventDefault();
    } else {
        password.style.border = "2px none"
    }
});

const firstName = document.getElementById('first-name');
const lastName = document.getElementById('last-name');
const nickname = document.getElementById('nickname');
const dateOfBirth = document.getElementById('date-of-birth');
const email = document.getElementById('email');
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirm-password');
const accountForm = document.getElementById('account-form');
const emailRegex = new RegExp('^\\w+([-+.\']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$');
const passwordRegex = new RegExp('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$');
const dataRegex = new RegExp('((0[1-9])|([12][0-9])|(3[01]))-((0[1-9])|(1[012]))-((19)|(2[012]))\\d{2}')

accountForm.addEventListener('submit', (e) => {
    if (firstName.value.length === 0 &
        lastName.value.length === 0 &
        nickname.value.length === 0 &
        dateOfBirth.value.length === 0 &
        email.value.length === 0 &
        password.value.length === 0) {
        e.preventDefault();
    }

    if (dateOfBirth.value.length != 0 &&
        !dataRegex.test(dateOfBirth.value)) {
        dateOfBirth.style.border = "2px solid red"
        e.preventDefault();
    } else {
        dateOfBirth.style.border = "2px none"
    }

    if (email.value.length != 0 &&
        (email.value.length > 100 ||
        !emailRegex.test(email.value))) {
        email.style.border = "2px solid red"
        e.preventDefault();
    } else {
        email.style.border = "2px none"
    }

    if (password.value.length != 0 &&
        (password.value.length < 8 ||
        password.value.length > 20 ||
        !passwordRegex.test(password.value))) {
        password.style.border = "2px solid red";
        e.preventDefault();
    } else {
        password.style.border = "2px none"
    }

    if (password.value.length != 0 &&
        (confirmPassword.value.length == 0 ||
        password.value.localeCompare(confirmPassword.value) != 0)) {
        confirmPassword.style.border = "2px solid red";
        e.preventDefault();
    } else {
        confirmPassword.style.border = "2px none"
    }
});

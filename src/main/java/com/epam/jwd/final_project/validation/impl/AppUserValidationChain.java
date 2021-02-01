package com.epam.jwd.final_project.validation.impl;

import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.validation.*;
import java.util.ArrayList;
import java.util.List;

public class AppUserValidationChain implements ValidationChain<AppUser> {

    private Validator validator;

    @Override
    public List<String> getValidationReport(AppUser user, ValidationType type) {
        List<String> validationErrors = new ArrayList<>();
        createValidationChain();
        validator.validate(user, validationErrors, type);

        return validationErrors;
    }

    private void createValidationChain() {
        LastNameValidator lastNameValidator = new LastNameValidator();
        NicknameValidator nicknameValidator = new NicknameValidator();
        DateOfBirthValidator dateValidator = new DateOfBirthValidator();
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();

        validator = new FirstNameValidator();
        validator.setNextValidator(lastNameValidator);
        lastNameValidator.setNextValidator(nicknameValidator);
        nicknameValidator.setNextValidator(dateValidator);
        dateValidator.setNextValidator(emailValidator);
        emailValidator.setNextValidator(passwordValidator);
    }

}

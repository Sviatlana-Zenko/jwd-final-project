package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppUser;
import java.util.List;

public class PasswordValidator extends Validator<AppUser> {

    private static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    private static final int MIN_LENGTH = 8;
    private static final int MAX_LENGTH = 20;

    public void validate(AppUser user, List<String> validationErrors, ValidationType type) {
        String password = user.getPassword();

        if (password == null || password.length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'password' field is not filled");
            }
        } else {
            if (!password.matches(PASSWORD_REGEX)) {
                validationErrors.add("'password' field has wrong format");
            }
            if (password.length() > MAX_LENGTH) {
                validationErrors.add("'password' is longer than " +  MAX_LENGTH + " characters");
            }
            if (password.length() < MIN_LENGTH) {
                validationErrors.add("'password' must contain at least 8 characters");
            }
        }

        super.validate(user, validationErrors, type);
    }

}

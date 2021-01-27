package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppUser;
import java.util.List;

public class EmailValidator extends Validator<AppUser> {

    private static final String EMAIL_REGEX = "^\\w+([-+.\']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    private static final int MAX_LENGTH = 100;

    public void validate(AppUser user, List<String> validationErrors, ValidationType type) {
        String email = user.getEmail();

        if (email == null || email.length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'email' field is not filled");
            }
        } else {
            if (!email.matches(EMAIL_REGEX)) {
                validationErrors.add("'email' field has wrong format");
            }

            if (email.length() > MAX_LENGTH) {
                validationErrors.add("'email' is longer than " +  MAX_LENGTH + " characters");
            }
        }

        super.validate(user, validationErrors, type);
    }

}

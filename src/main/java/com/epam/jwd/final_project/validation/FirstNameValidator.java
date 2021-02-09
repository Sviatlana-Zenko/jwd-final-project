package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppUser;
import java.util.List;

public class FirstNameValidator extends Validator<AppUser> {

    private static final int MAX_LENGTH = 25;

    @Override
    public void validate(AppUser user, List<String> validationErrors, ValidationType type) {
        String firstName = user.getFirstName();

        if (firstName == null || firstName.length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'first name' field is not filled");
            }
        } else {
            if (firstName.length() > MAX_LENGTH) {
                validationErrors.add("'first name' is longer than " + MAX_LENGTH + " characters");
            }
        }

        super.validate(user, validationErrors, type);
    }

}

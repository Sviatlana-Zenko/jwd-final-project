package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppUser;
import java.util.List;

public class LastNameValidator extends Validator<AppUser> {

    private static final int MAX_LENGTH = 25;

    @Override
    public void validate(AppUser user, List<String> validationErrors, ValidationType type) {
        String lastName = user.getLastName();

        if (lastName == null || lastName.length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'last name' field is not filled");
            }
        } else {
            if (lastName.length() > MAX_LENGTH) {
                validationErrors.add("'last name' is longer than " +  MAX_LENGTH + " characters");
            }
        }

        super.validate(user, validationErrors, type);
    }

}

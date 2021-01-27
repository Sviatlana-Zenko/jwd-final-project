package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppUser;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class DateOfBirthValidator extends Validator<AppUser> {

    private final static String DATE_REGEX = "19\\d{2}-((0[1-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01]))";

    public void validate(AppUser user, List<String> validationErrors, ValidationType type) {
        LocalDate dateOfBirth = user.getDateOfBirth();

        if (dateOfBirth == null || dateOfBirth.toString().length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'date of birth' field is not filled");
            }
        } else {
            if (!dateOfBirth.toString().matches(DATE_REGEX)) {
                validationErrors.add("'date of birth' field has wrong format");
            }

            Period periodBetween = Period.between(dateOfBirth, LocalDate.now());
            if (periodBetween.isNegative()) {
                validationErrors.add("'date of birth' field has a date from the future");
            }
        }

        super.validate(user, validationErrors, type);
    }

}

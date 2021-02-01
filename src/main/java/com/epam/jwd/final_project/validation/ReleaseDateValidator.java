package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class ReleaseDateValidator extends Validator<CinemaProduct> {

    private final static String DATE_REGEX =
            "((19)|(2[012]))\\d{2}-((0[1-9])|(1[012]))-((0[1-9])|([12][0-9])|(3[01]))";

    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        LocalDate releaseDate = product.getReleaseDate();

        if (releaseDate == null || releaseDate.toString().length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'release date' field is not filled or has wrong format");
            }
        } else {
            if (!releaseDate.toString().matches(DATE_REGEX)) {
                validationErrors.add("'release date' field has wrong format");
            }

            Period periodBetween = Period.between(releaseDate, LocalDate.now());
            if (periodBetween.isNegative()) {
                validationErrors.add("'release date' field has a date from the future");
            }
        }

        super.validate(product, validationErrors, type);
    }

}

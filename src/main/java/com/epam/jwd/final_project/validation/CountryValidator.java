package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;

import java.util.List;

public class CountryValidator extends Validator<CinemaProduct> {

    private static final int MAX_LENGTH = 50;

    @Override
    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        String country = product.getCountry();

        if (type == ValidationType.CREATE_OBJECT & (country == null || country.length() == 0)) {
            validationErrors.add("'country' field is not filled");
        } else {
            if (country != null && country.length() > MAX_LENGTH) {
                validationErrors.add("'country' is longer than " + MAX_LENGTH +  "characters");
            }
        }

        super.validate(product, validationErrors, type);
    }
}

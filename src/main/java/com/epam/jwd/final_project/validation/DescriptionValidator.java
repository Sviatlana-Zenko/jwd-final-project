package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;

import java.util.List;

public class DescriptionValidator extends Validator<CinemaProduct> {

    private static final int MAX_LENGTH = 1500;

    @Override
    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        String description = product.getDescription();

        if (type == ValidationType.CREATE_OBJECT & (description == null || description.length() == 0)) {
            validationErrors.add("'description' field is not filled");
        } else {
            if (description != null && description.length() > MAX_LENGTH) {
                validationErrors.add("'description' is longer than " + MAX_LENGTH +  "characters");
            }
        }

        super.validate(product, validationErrors, type);
    }
}

package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.Quote;

import java.util.List;

public class TitleValidator extends Validator<CinemaProduct> {

    private static final int MAX_LENGTH = 100;

    @Override
    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        String title = product.getTitle();

        if (type == ValidationType.CREATE_OBJECT & (title == null || title.length() == 0)) {
            validationErrors.add("'title' field is not filled");
        } else {
            if (title != null && title.length() > MAX_LENGTH) {
                validationErrors.add("'title' is longer than " + MAX_LENGTH +  "characters");
            }
        }

        super.validate(product, validationErrors, type);
    }
}

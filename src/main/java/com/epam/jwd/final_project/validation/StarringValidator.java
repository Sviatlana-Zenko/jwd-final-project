package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import java.util.List;

public class StarringValidator extends Validator<CinemaProduct> {

    private static final int MAX_LENGTH = 250;

    @Override
    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        String starring = product.getStarring();

        if (type == ValidationType.CREATE_OBJECT &
                (starring == null || starring.length() == 0)) {
            validationErrors.add("'starring' field is not filled");
        } else {
            if (starring != null && starring.length() > MAX_LENGTH) {
                validationErrors.add("'starring' is longer than " + MAX_LENGTH +  "characters");
            }
        }

        super.validate(product, validationErrors, type);
    }
}

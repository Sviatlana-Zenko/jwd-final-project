package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.Movie;
import java.util.List;

public class DirectedByValidator extends Validator<CinemaProduct> {

    private static final int MAX_LENGTH = 100;

    @Override
    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        String directedBy = ((Movie) product).getDirectedBy();

        if (type == ValidationType.CREATE_OBJECT
                & (directedBy == null || directedBy.length() == 0)) {
            validationErrors.add("'directedBy' field is not filled");
        } else {
            if (directedBy != null && directedBy.length() > MAX_LENGTH) {
                validationErrors.add("'directedBy' is longer than " + MAX_LENGTH +  "characters");
            }
        }

        super.validate(product, validationErrors, type);
    }
}

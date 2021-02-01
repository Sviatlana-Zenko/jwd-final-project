package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.Movie;
import java.util.List;

public class ProducedByValidator extends Validator<CinemaProduct> {

    private static final int MAX_LENGTH = 100;

    @Override
    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        String producedBy = ((Movie) product).getProducedBy();

        if (type == ValidationType.CREATE_OBJECT
                & (producedBy == null || producedBy.length() == 0)) {
            validationErrors.add("'producedBy' field is not filled");
        } else {
            if (producedBy != null && producedBy.length() > MAX_LENGTH) {
                validationErrors.add("'producedBy' is longer than "
                        + MAX_LENGTH +  "characters");
            }
        }

        super.validate(product, validationErrors, type);
    }
}

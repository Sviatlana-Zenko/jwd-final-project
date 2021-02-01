package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.Movie;

import java.util.List;

public class BudgetValidator extends Validator<CinemaProduct> {

    public void validate(CinemaProduct product, List<String> validationErrors, ValidationType type) {
        Integer budget = ((Movie) product).getBudget();

        if (budget == null) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'budget' field is not filled or has wrong format");
            }
        } else {
            if (budget < 0) {
                validationErrors.add("'budget' can't be negative");
            }
        }

        super.validate(product, validationErrors, type);
    }

}

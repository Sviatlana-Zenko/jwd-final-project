package com.epam.jwd.final_project.validation.impl;

import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.validation.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewValidationChain implements ValidationChain<Review> {

    private Validator validator;

    @Override
    public List<String> getValidationReport(Review review, ValidationType type) {
        List<String> validationErrors = new ArrayList<>();
        createValidationChain();
        validator.validate(review, validationErrors, type);

        return validationErrors;
    }

    private void createValidationChain() {
        ReviewTextValidator textValidator = new ReviewTextValidator();
        validator = new ReviewSummaryValidator();
        validator.setNextValidator(textValidator);
    }

}

package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.Review;
import java.util.List;

public class ReviewSummaryValidator extends Validator<Review> {

    private static final int MAX_LENGTH = 100;

    @Override
    public void validate(Review review, List<String> validationErrors, ValidationType type) {
        String summary = review.getReviewSummary();

        if (summary == null || summary.length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'summary' field is not filled");
            }
        } else {
            if (summary.length() > MAX_LENGTH) {
                validationErrors.add("'summary' is longer than " +  MAX_LENGTH + " characters");
            }
        }

        super.validate(review, validationErrors, type);
    }
    
}

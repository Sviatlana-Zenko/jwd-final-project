package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.Review;
import java.util.List;

public class ReviewTextValidator extends Validator<Review> {

    private static final int MAX_LENGTH = 1500;

    @Override
    public void validate(Review review, List<String> validationErrors, ValidationType type) {
        String text = review.getReviewText();

        if (text == null || text.length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'review text' field is not filled");
            }
        } else {
            if (text.length() > MAX_LENGTH) {
                validationErrors.add("'review text' is longer than " +  MAX_LENGTH + " characters");
            }
        }

        super.validate(review, validationErrors, type);
    }
    
}

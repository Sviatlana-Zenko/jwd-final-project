package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.Quote;
import java.util.List;

public class QuoteTitleValidator extends Validator<Quote> {

    private static final int MAX_LENGTH = 100;

    @Override
    public void validate(Quote quote, List<String> validationErrors, ValidationType type) {
        String title = quote.getProductTitle();

        if (type == ValidationType.CREATE_OBJECT & (title == null || title.length() == 0)) {
            validationErrors.add("'movie/TV series title' field is not filled");
        } else {
            if (title != null && title.length() > MAX_LENGTH) {
                validationErrors.add("'movie/TV series title' is longer than 100 characters");
            }
        }

        super.validate(quote, validationErrors, type);
    }
}

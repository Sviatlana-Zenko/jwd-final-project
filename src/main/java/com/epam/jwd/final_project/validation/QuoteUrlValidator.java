package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.Quote;
import java.util.List;

public class QuoteUrlValidator extends Validator<Quote> {

    private static final int MAX_LENGTH = 80;
    private static final String URL_PATTERN = "^https://drive\\.google\\.com/.+";

    @Override
    public void validate(Quote quote, List<String> validationErrors, ValidationType type) {
        String posterUrl = quote.getPosterUrl();
        if (type == ValidationType.CREATE_OBJECT &
                (posterUrl == null || posterUrl.length() == 0)) {
            validationErrors.add("'poster url' field is not filled");
        } else {
            if (posterUrl != null && posterUrl.length() != 0) {
                if (posterUrl.length() > MAX_LENGTH) {
                    validationErrors.add("'poster url' is longer than 80 characters");
                } else {
                    if (!posterUrl.matches(URL_PATTERN)) {
                        validationErrors.add("'poster url' doesn't start with 'https://drive.google.com/...'");
                    }
                }
            }
        }

        super.validate(quote, validationErrors, type);
    }
    
}

package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.Quote;
import java.util.List;

public class QuoteTextValidator extends Validator<Quote> {

    private static final int MAX_LENGTH = 100;

    @Override
    public void validate(Quote quote, List<String> validationErrors, ValidationType type) {
        String quoteText = quote.getQuoteText();
        if (type == ValidationType.CREATE_OBJECT &
                (quoteText == null || quoteText.length() == 0)) {
            validationErrors.add("'quote text' field is not filled");
        } else {
            if (quoteText != null && quoteText.length() > MAX_LENGTH) {
                validationErrors.add("'quote text' is longer than " + MAX_LENGTH + " characters");
            }
        }

        super.validate(quote, validationErrors, type);
    }

}

package com.epam.jwd.final_project.validation.impl;

import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.validation.*;

import java.util.ArrayList;
import java.util.List;

public class QuoteValidationChain implements ValidationChain<Quote> {

    private Validator validator;

    @Override
    public List<String> getValidationReport(Quote quote, ValidationType type) {
        List<String> validationErrors = new ArrayList<>();
        createValidationChain();
        validator.validate(quote, validationErrors, type);

        return validationErrors;
    }

    private void createValidationChain() {
        QuoteTextValidator quoteTextValidator = new QuoteTextValidator();
        QuoteUrlValidator posterUrlValidator = new QuoteUrlValidator();

        validator = new QuoteTitleValidator();
        validator.setNextValidator(quoteTextValidator);
        quoteTextValidator.setNextValidator(posterUrlValidator);
    }

}

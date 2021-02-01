package com.epam.jwd.final_project.validation.impl;

import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.validation.*;
import java.util.ArrayList;
import java.util.List;

public class MovieValidationChain implements ValidationChain<CinemaProduct> {

    private Validator validator;

    @Override
    public List<String> getValidationReport(CinemaProduct product, ValidationType type) {
        List<String> validationErrors = new ArrayList<>();
        createValidationChain();
        validator.validate(product, validationErrors, type);

        return validationErrors;
    }

    private void createValidationChain() {
        TitleValidator titleValidator = new TitleValidator();
        StarringValidator starringValidator = new StarringValidator();
        ReleaseDateValidator dateValidator = new ReleaseDateValidator();
        PosterUrlValidator urlValidator = new PosterUrlValidator();
        DirectedByValidator directedByValidator = new DirectedByValidator();
        ProducedByValidator producedByValidator = new ProducedByValidator();
        DescriptionValidator descriptionValidator = new DescriptionValidator();
        RunningTimeValidator timeValidator = new RunningTimeValidator();
        BudgetValidator budgetValidator = new BudgetValidator();
        BoxOfficeValidator boxOfficeValidator = new BoxOfficeValidator();

        validator = new CountryValidator();
        validator.setNextValidator(titleValidator);
        titleValidator.setNextValidator(starringValidator);
        starringValidator.setNextValidator(dateValidator);
        dateValidator.setNextValidator(urlValidator);
        urlValidator.setNextValidator(directedByValidator);
        directedByValidator.setNextValidator(producedByValidator);
        producedByValidator.setNextValidator(descriptionValidator);
        descriptionValidator.setNextValidator(timeValidator);
        timeValidator.setNextValidator(budgetValidator);
        budgetValidator.setNextValidator(boxOfficeValidator);
    }

}

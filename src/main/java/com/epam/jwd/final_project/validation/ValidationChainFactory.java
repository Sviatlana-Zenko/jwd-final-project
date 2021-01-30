package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppEntity;
import com.epam.jwd.final_project.validation.impl.AppUserValidationChain;
import com.epam.jwd.final_project.validation.impl.QuoteValidationChain;
import com.epam.jwd.final_project.validation.impl.ReviewValidationChain;

public class ValidationChainFactory {

    public static final ValidationChainFactory INSTANCE = new ValidationChainFactory();

    private ValidationChainFactory() {
    }

    public ValidationChain createValidationChain(AppEntity entity) {
        ValidationChain validationChain = null;
        String entityClass = entity.getClass().getSimpleName();

        switch (entityClass) {
            case "AppUser":
                validationChain = new AppUserValidationChain();
                break;
            case "Quote":
                validationChain = new QuoteValidationChain();
                break;
            case "Review":
                validationChain = new ReviewValidationChain();
                break;
            default:
                validationChain = null;
                break;
        }

        return validationChain;
    }

}

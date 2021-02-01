package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppEntity;
import java.util.List;

public abstract class Validator<T extends AppEntity> {

    private Validator nextValidator;

    public void setNextValidator(Validator nextValidator) {
        this.nextValidator = nextValidator;
    }

    public void validate(T t, List<String> validationErrors, ValidationType type) {
        if (nextValidator != null) {
            nextValidator.validate(t, validationErrors, type);
        }
    }

}

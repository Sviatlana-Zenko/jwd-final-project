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

//public abstract class Validator<T extends AppEntity> {
//
//    private Validator nextValidator;
//
//    @Override
//    public void setNextValidator(Validator nextValidator) {
//        this.nextValidator = nextValidator;
//    }
//
//    @Override
//    public void validate(T t, List<String> validationErrors) {
//        if (nextValidator != null) {
//            nextValidator.validate(t, validationErrors);
//        }
//    }
//
//}

//public abstract class AbstractValidator implements Validator {
//
//    private Validator nextValidator;
//
//    @Override
//    public void setNextValidator(Validator nextValidator) {
//        this.nextValidator = nextValidator;
//    }
//
//    @Override
//    public void validate(AppEntity appEntity, List<String> validationErrors) {
//        if (nextValidator != null) {
//            nextValidator.validate(appEntity, validationErrors);
//        }
//    }
//
//}

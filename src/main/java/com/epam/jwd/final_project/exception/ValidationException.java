package com.epam.jwd.final_project.exception;

import java.util.List;

public class ValidationException extends Exception {
    private final String entityName;
    private final List<String> validationErrors;

    public ValidationException(String entityName, List<String> errors) {
        super();
        this.entityName = entityName;
        this.validationErrors = errors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    @Override
    public String getMessage() {
        return entityName + " wasn't created, because of the following errors: " + validationErrors;
    }

    //public FigureException() {
    //    }
    //
    //    public FigureException(String message) {
    //        super(message);
    //    }
    //
    //    public FigureException(String message, Throwable cause) {
    //        super(message, cause);
    //    }
    //
    //    public FigureException(Throwable cause) {
    //        super(cause);
    //    }
}

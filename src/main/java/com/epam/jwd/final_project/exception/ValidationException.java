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

    public ValidationException(Throwable cause, String entityName, List<String> validationErrors) {
        super(cause);
        this.entityName = entityName;
        this.validationErrors = validationErrors;
    }

    public ValidationException(String message, String entityName, List<String> validationErrors) {
        super(message);
        this.entityName = entityName;
        this.validationErrors = validationErrors;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    @Override
    public String getMessage() {
        return entityName + " wasn't created, because of the following errors: " + validationErrors;
    }

}

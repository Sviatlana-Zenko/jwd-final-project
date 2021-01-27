package com.epam.jwd.final_project.exception;

public class DatabaseInteractionException extends Exception {

    public DatabaseInteractionException() {
    }

    public DatabaseInteractionException(String message) {
        super(message);
    }

    public DatabaseInteractionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseInteractionException(Throwable cause) {
        super(cause);
    }

}

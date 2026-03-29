package com.btoy.trial.persistence.exception;

public class TriAuthEntityNotFoundException extends RuntimeException {

    public TriAuthEntityNotFoundException() {
    }

    public TriAuthEntityNotFoundException(String message) {
        super(message);
    }

    public TriAuthEntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TriAuthEntityNotFoundException(Throwable cause) {
        super(cause);
    }
}

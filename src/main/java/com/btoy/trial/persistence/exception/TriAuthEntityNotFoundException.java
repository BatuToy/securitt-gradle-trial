package com.btoy.trial.persistence.exception;

import com.btoy.trial.web.exception.BaseException;

import java.util.Map;

public class TriAuthEntityNotFoundException extends PersistenceException {

    public TriAuthEntityNotFoundException(Throwable cause, Map<String, ?> params) {
        super(cause, params);
    }

    public TriAuthEntityNotFoundException(String message, Throwable cause, Map<String, ?> params) {
        super(message, cause, params);
    }

    public TriAuthEntityNotFoundException(String message, Map<String, ?> params) {
        super(message, params);
    }

    public TriAuthEntityNotFoundException(Map<String, ?> params) {
        super(params);
    }

    public TriAuthEntityNotFoundException() {
        super();
    }
}

package com.btoy.trial.persistence.exception;

import com.btoy.trial.web.exception.BaseException;

import java.util.Map;

/*
 * @created 02/04/2026 ~~ 19:52
 * author: batu
 */
public class PersistenceException extends BaseException {

    public PersistenceException() {
    }

    public PersistenceException(Map<String, ?> params) {
        super(params);
    }

    public PersistenceException(String message, Map<String, ?> params) {
        super(message, params);
    }

    public PersistenceException(String message, Throwable cause, Map<String, ?> params) {
        super(message, cause, params);
    }

    public PersistenceException(Throwable cause, Map<String, ?> params) {
        super(cause, params);
    }
}

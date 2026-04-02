package com.btoy.trial.persistence.exception;

import com.btoy.trial.web.exception.BaseException;

import java.util.Map;

/*
 * @created 28/03/2026 ~~ 18:05
 * author: batu
 */
public class TriAuthUpdateFailedException extends PersistenceException {

    public TriAuthUpdateFailedException(String message, Map<String, ?> params) {
        super(message, params);
    }

    public TriAuthUpdateFailedException(Map<String, ?> params) {
        super(params);
    }

    public TriAuthUpdateFailedException() {
        super();
    }
}

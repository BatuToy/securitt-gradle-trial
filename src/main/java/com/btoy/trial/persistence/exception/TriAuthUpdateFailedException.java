package com.btoy.trial.persistence.exception;

/*
 * @created 28/03/2026 ~~ 18:05
 * author: batu
 */
public class TriAuthUpdateFailedException extends RuntimeException {
    public TriAuthUpdateFailedException() {
    }

    public TriAuthUpdateFailedException(String message) {
        super(message);
    }

    public TriAuthUpdateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TriAuthUpdateFailedException(Throwable cause) {
        super(cause);
    }
}

package com.btoy.trial.web.response;

/*
 * @created 31/03/2026 ~~ 19:15
 * author: batu
 */

import com.btoy.trial.persistence.exception.BaseException;

import java.util.Arrays;

public class ErrorResponse {

    private final String stackTrace;

    public ErrorResponse(StackTraceElement[] stackTrace) {
        this.stackTrace = Arrays.toString(stackTrace);;
    }

    public static ErrorResponse of(StackTraceElement[] stackTraceElements) {
        return new ErrorResponse(stackTraceElements);
    }
}

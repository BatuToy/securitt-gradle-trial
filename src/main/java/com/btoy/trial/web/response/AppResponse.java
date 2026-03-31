package com.btoy.trial.web.response;

import com.btoy.trial.persistence.exception.BaseException;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class AppResponse<T> {

    public static final HttpStatus DEFAULT_SUCCESS_CODE = HttpStatus.OK;
    public static final HttpStatus DEFAULT_ERROR_CODE = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final Integer EMPTY_CODE = 0;

    private final int code;
    private final String message;
    private final T data;

    private Map<String, ?> params;
    private final ErrorResponse error;

    public AppResponse(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.error = null;
    }

    public AppResponse(StackTraceElement[] trace, String message, Map<String, ?> params, Integer code) {
        this.code = Objects.equals(code, EMPTY_CODE) ? DEFAULT_ERROR_CODE.value() : code;
        this.error = ErrorResponse.of(trace);
        this.message = message;
        this.params = params;
        this.data = null;
    }

    public AppResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
        this.error = null;
    }

    public AppResponse(String message) {
        this.code = DEFAULT_SUCCESS_CODE.value();
        this.message = message;
        this.data = null;
        this.error = null;
    }

    public AppResponse(T data, String message) {
        this.code = DEFAULT_SUCCESS_CODE.value();
        this.data = data;
        this.message = message;
        this.error = null;
    }

    public static <T> AppResponse<T> of(T data, String message, Integer code) {
        return new AppResponse<>(data, message, code);
    }

    public static <T> AppResponse<T> of(T data, String message) {
        return new AppResponse<>(data, message);
    }

    public static <T> AppResponse<T> of(String message) {
        return new AppResponse<>(message);
    }

    public static <T> AppResponse<T> of(StackTraceElement[] traceLog, String message, Map<String, ?> params) {
        return new AppResponse<>(traceLog, message, params, EMPTY_CODE);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public ErrorResponse getError() {
        return error;
    }

    // Protected from outside.
    public Map<String, ?> getParams() {
        return Collections.unmodifiableMap(this.params);
    }
}

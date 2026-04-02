package com.btoy.trial.web.response;

import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;

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

    private final Map<?, ?> params;
    private final ErrorResponse error;

    public AppResponse(T data, String message, Integer code, Map<?, ?> params) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.error = null;
        this.params = handleParams(params);
    }

    public AppResponse(StackTraceElement[] trace, String message, Map<?, ?> params, Integer code) {
        this.code = code;
        this.error = ErrorResponse.of(trace);
        this.message = message;
        this.data = null;
        this.params = handleParams(params);
    }

    public AppResponse(Integer code, String message, Map<?, ?> params) {
        this.code = code;
        this.message = message;
        this.data = null;
        this.error = null;
        this.params = handleParams(params);

    }

    public AppResponse(String message, Map<?, ?> params) {
        this.code = DEFAULT_SUCCESS_CODE.value();
        this.message = message;
        this.data = null;
        this.error = null;
        this.params = handleParams(params);
    }

    public AppResponse(T data, String message, Map<?, ?> params) {
        this.params = handleParams(params);
        this.code = DEFAULT_SUCCESS_CODE.value();
        this.data = data;
        this.message = message;
        this.error = null;
    }

    public static <T> AppResponse<T> of(T data, String message, Integer code) {
        return new AppResponse<>(data, message, code, Map.of());
    }

    public static <T> AppResponse<T> of(T data, String message) {
        return new AppResponse<>(data, message, Map.of());
    }

    public static <T> AppResponse<T> of(String message) {
        return new AppResponse<>(message, Map.of());
    }

    public static <T> AppResponse<T> of(StackTraceElement[] traceLog, String message, Map<?, ?> params, @Nullable Integer code) {
        HttpStatus status = Objects.isNull(code) ? DEFAULT_ERROR_CODE : HttpStatus.valueOf(code);
        return new AppResponse<>(traceLog, message, params, status.value());
    }

    private static Map<?, ?> handleParams(Map<?, ?> params) {
        return (params == null || params.isEmpty()) ? Collections.emptyMap() : params;
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
    public Map<?, ?> getParams() {
        return Collections.unmodifiableMap(this.params);
    }
}

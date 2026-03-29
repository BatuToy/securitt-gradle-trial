package com.btoy.trial.web.response;

import org.springframework.http.HttpStatus;

public class AppResponse<T> {

    private static final HttpStatus DEFAULT_CODE = HttpStatus.OK;

    private final int code;
    private final String message;
    private final T data;

    public AppResponse(T data, String message, Integer code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public AppResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public AppResponse(String message) {
        this.code = DEFAULT_CODE.value();
        this.data = null;
        this.message = message;
    }

    public AppResponse(T data, String message) {
        this.code = DEFAULT_CODE.value();
        this.data = data;
        this.message = message;
    }

    public static <T> AppResponse<T> of(T data, String message, Integer code) {
        return new AppResponse<>(data, message, code);
    }

    public static <T> AppResponse<T> of (T data, String message) {
        return new AppResponse<>(data, message);
    }

    public static <T> AppResponse<T> of(String message) {
        return new AppResponse<>(message);
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
}

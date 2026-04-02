package com.btoy.trial.web.exception;


import com.btoy.trial.constants.Log;
import com.btoy.trial.web.response.AppResponse;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static com.btoy.trial.web.security.util.ObjectUtils.*;
import static com.btoy.trial.web.security.util.ObjectUtils.isMapNullOrEmpty;

public class BaseException extends RuntimeException implements ParametricException {

    private static final String M_PARAM_VALUES_NOT_NULL = "Exception Parameters Can Not Be Null";
    private static final String M_MESSAGE_NOT_BLANK_OR_NULL = "Provided Exception Message Can Not Be Null Or Blank";

    private final transient Map<?, ?> params;

    public BaseException() {
        this.params = Collections.emptyMap();
    }

    public BaseException(Map<?, ?> params) {
        super();
        this.params = params;
    }

    public BaseException(String message, Map<?, ?> params) {
        super(isParamsProvided(params) ? formatMessage(message, params.values()) : message);
        this.params = params;
    }

    public BaseException(String message, Map<?, ?> params, Throwable cause) {
        final String msg = isParamsProvided(params) ? formatMessage(message, params.values()): message;
        super(msg, cause);
        this.params = nvlMap(params);
    }

    public BaseException(Throwable cause, Map<String, ?> params) {
        super(cause);
        this.params = params;
    }

    private static String formatMessage(String message, Collection<?> paramValues) {
        if (!StringUtils.hasText(message)) {
            Log.LOGGER.severe(M_MESSAGE_NOT_BLANK_OR_NULL);
            throw new IllegalArgumentException(M_MESSAGE_NOT_BLANK_OR_NULL);
        }
        Objects.requireNonNull(paramValues, M_PARAM_VALUES_NOT_NULL);
        return String.format(message, paramValues);
    }

    // Protect Params from outsider's.
    public Map<?, ?> getParams() {
        return Collections.unmodifiableMap(this.params);
    }

    @Override
    public Boolean hasAnyParam() {
        return isParamsProvided(params);
    }

    public <T> AppResponse<T> toAppResponse(Integer code) {
        return AppResponse.of(super.getStackTrace(), super.getMessage(), getParams(), code);
    }

    public <T> AppResponse<T> toAppResponse() {
        return AppResponse.of(super.getStackTrace(), super.getMessage(), getParams(), null);
    }

    private static boolean isParamsProvided(Map<?, ?> params) {
        return !isMapNullOrEmpty(params);
    }
}

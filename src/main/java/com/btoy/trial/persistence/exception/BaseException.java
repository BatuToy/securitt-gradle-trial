package com.btoy.trial.persistence.exception;


import com.btoy.trial.constants.Log;
import com.btoy.trial.web.response.AppResponse;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public class BaseException extends RuntimeException{

    private static final String M_PARAM_VALUES_NOT_NULL = "Exception Parameters Can Not Be Null";
    private static final String M_MESSAGE_NOT_BLANK_OR_NULL = "Provided Exception Message Can Not Be Null Or Blank";

    private final transient Map<String, ?> params;

    public BaseException() {
        this.params = Collections.emptyMap();
    }

    public BaseException(Map<String, ?> params) {
        super();
        this.params = params;
    }

    public BaseException(String message, Map<String, ?> params) {
        String formattedMessage = formatMessage(message, params.values());
        super(formattedMessage);
        this.params = params;
    }

    public BaseException(String message, Throwable cause, Map<String, ?> params) {
        String formattedMessage = formatMessage(message, params.values());
        super(formattedMessage, cause);
        this.params = params;
    }

    public BaseException(Throwable cause, Map<String, ?> params) {
        super(cause);
        this.params = params;
    }

    private static String formatMessage(String message, Collection<?> paramValues) {
        if (StringUtils.hasText(message)) {
            Log.LOGGER.severe(M_MESSAGE_NOT_BLANK_OR_NULL);
            throw new IllegalArgumentException(M_MESSAGE_NOT_BLANK_OR_NULL);
        }
        Objects.requireNonNull(paramValues, M_PARAM_VALUES_NOT_NULL);
        return String.format(message, paramValues);
    }

    // Protect Params from outsider's.
    public Map<String, ?> getParams() {
        return Collections.unmodifiableMap(this.params);
    }

    public <T> AppResponse<T> toAppResponse() {
        return AppResponse.of(super.getStackTrace(), super.getMessage(), getParams());
    }
}

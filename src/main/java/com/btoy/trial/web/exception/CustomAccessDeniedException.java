package com.btoy.trial.web.exception;

import org.springframework.security.access.AccessDeniedException;

import java.util.Map;

import static com.btoy.trial.web.security.util.ObjectUtils.*;

/*
 * @created 02/04/2026 ~~ 20:06
 * author: batu
 */

public class CustomAccessDeniedException extends AccessDeniedException implements ParametricException {

    private final transient Map<?, ?> params;

    public CustomAccessDeniedException(String msg, Map<String, ?> params) {
        super(msg);
        this.params = nvlMap(params);
    }

    public CustomAccessDeniedException(String msg, Throwable cause, Map<?, ?> params) {
        super(msg, cause);
        this.params = nvlMap(params);
    }

    @Override
    public Map<?, ?> getParams() {
        return this.params;
    }

    @Override
    public Boolean hasAnyParam() {
        return !isMapNullOrEmpty(this.params);
    }
}

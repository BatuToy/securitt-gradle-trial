package com.btoy.trial.web.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.AuthenticationException;

import java.util.Map;

import static com.btoy.trial.web.security.util.ObjectUtils.*;

/*
 * @created 02/04/2026 ~~ 20:15
 * author: batu
 */
public class CustomAuthenticationException extends AuthenticationException implements ParametricException {

    private final transient Map<?, ?> params;

    public CustomAuthenticationException(@Nullable String msg, Throwable cause, Map<?, ?> params) {
        super(msg, cause);
        this.params = nvlMap(params);
    }

    public CustomAuthenticationException(@Nullable String msg, Map<?, ?> params) {
        super(msg);
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

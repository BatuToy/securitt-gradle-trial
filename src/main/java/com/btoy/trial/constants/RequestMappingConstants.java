package com.btoy.trial.constants;


import org.springframework.data.domain.Sort;

/*
 * @created 15/03/2026 ~~ 17:29
 * author: batu
 */
public final class RequestMappingConstants {

    private RequestMappingConstants() {
        // protected for initialization.
    }

    public static final String BASE_AUTH_ENDPOINT = "${trial-app.endpoints.authentication.base}";
    public static final String LOG_IN = "${trial-app.endpoints.authentication.login}";
    public static final String LOG_OUT = "${trial-app.endpoints.authentication.logout}";
    public static final String REGISTER = "${trial-app.endpoints.authentication.register}";
}

package com.btoy.trial.constants;

/*
 * @created 15/03/2026 ~~ 17:35
 * author: batu
 */
public final class Authorities {

    private Authorities() {
        // protected
    }

    private static final String TRI_AUTH_PREFIX = "hasAnyAuthority(";
    private static final String TRI_AUTH_SUFFIX = ")";

    public static final String AUTHORITY_LOGOUT = TRI_AUTH_PREFIX + " 'logout' " + TRI_AUTH_SUFFIX;
}

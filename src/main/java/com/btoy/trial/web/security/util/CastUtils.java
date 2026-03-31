package com.btoy.trial.web.security.util;

/*
 * @created 31/03/2026 ~~ 20:48
 * author: batu
 */
public class CastUtils {

    public static <T> T cast(Class<T> clazz, Object object) {
        return clazz.cast(object);
    }
}

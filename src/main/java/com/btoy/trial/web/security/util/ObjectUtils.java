package com.btoy.trial.web.security.util;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

/*
 * @created 01/04/2026 ~~ 20:11
 * author: batu
 */
public final class ObjectUtils {

    private ObjectUtils() {
        throw new UnsupportedOperationException();
    }

    public static <T> T nvl(T data) {
        return Objects.isNull(data) ? null : data;
    }

    public static String fromStackTrace(StackTraceElement[] elements) {
        return Arrays.toString(elements);
    }

    public static boolean isMapNullOrEmpty(Map<?, ?> hashtable) {
        return Objects.isNull(hashtable) || hashtable.isEmpty();
    }

    public static Map<?, ?> nvlMap(Map<?, ?> map) {
        return isMapNullOrEmpty(map) ? Map.of() : map;
    }
}

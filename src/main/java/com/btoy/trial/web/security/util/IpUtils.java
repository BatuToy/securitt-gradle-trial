package com.btoy.trial.web.security.util;

import com.btoy.trial.constants.ApplicationConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.*;

/*
 * @created 16/03/2026 ~~ 12:35
 * author: batu
 */
public final class IpUtils {

    private static final List<String> ipHeaders;

    private IpUtils() {
        // protected from initializing.
    }

    static {
        ipHeaders = new ArrayList<>();
        ipHeaders.add("X-FORWARDED-FOR");
        // ... keep adding possible ip header labels.
    }

    // TODO Keep developing here.
    public static String getIpFromRequest(HttpServletRequest request) {
        final String[] ipAddr = new String[]{ApplicationConstants.EMPTY_STRING};
        ipHeaders.forEach(header -> {
            if (!StringUtils.hasText(ipAddr[0])) {
                ipAddr[0] = request.getHeader(header);
            }
        });
        if (!StringUtils.hasText(ipAddr[0])) {
            ipAddr[0] = request.getRemoteAddr();
        }
        return ipAddr[0];
    }
}

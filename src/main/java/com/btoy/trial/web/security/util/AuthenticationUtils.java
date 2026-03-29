package com.btoy.trial.web.security.util;

import com.btoy.trial.constants.Log;
import com.btoy.trial.web.security.userdetails.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;
import java.util.Optional;

/*
 * @created 18/03/2026 ~~ 13:31
 * author: batu
 */
public final class AuthenticationUtils {

    private AuthenticationUtils() {
        //protected from initialization
    }

    private static SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    public static Optional<Authentication> getAuthentication() {
        Authentication authentication = getContext().getAuthentication();
        if (Objects.isNull(authentication)) {
            return Optional.empty();
        }
        return Optional.of(authentication);
    }

    /**
     *   If no Authentication Object found in the StrategyContext:
     *   @return false
     */
    public static boolean isAuthenticated() {
        Optional<Authentication> optAuthentication = getAuthentication();
        if (optAuthentication.isPresent()) {
            Authentication authentication = optAuthentication.get();
            return authentication.isAuthenticated();
        }
        return false;
    }

    public static Object getPrinciples() {
        final Optional<Authentication> optAuthentication = getAuthentication();
        if (optAuthentication.isEmpty()) {
            Log.LOGGER.severe("No Authentication Object found!");
            throw new SecurityException("No Authentication Object Found!");
        }
        final Object principal = optAuthentication.get().getPrincipal();
        if (principal instanceof UserDetails details) {
            if (details instanceof CustomUserDetails customUserDetails) {
                return customUserDetails;
            } else {
                return details;
            }
        } else {
            return principal;
        }
    }
}

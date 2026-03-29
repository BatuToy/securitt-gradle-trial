package com.btoy.trial.web.security.filter;

/*
 * @created 23/03/2026 ~~ 22:25
 * author: batu
 */

import com.btoy.trial.constants.Log;
import com.btoy.trial.web.security.token.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationPerRequestFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(TOKEN_HEADER);
        if (Objects.isNull(token)) {
            Log.LOGGER.severe("Token is not provided at all.");
        } else if (JwtTokenProvider.isTokenVerified(token)) {
            Log.LOGGER.severe("Token is not valid.");
        } else if (JwtTokenProvider.isTokenExpired(token)) {
            Log.LOGGER.severe("Token expired.");
        }
        final String subject = JwtTokenProvider.extractSubject(token);
        //...
    }
}

package com.btoy.trial.web.security.filter;

/*
 * @created 23/03/2026 ~~ 22:25
 * author: batu
 */

import com.btoy.trial.constants.Log;
import com.btoy.trial.web.security.token.JwtTokenProvider;
import com.btoy.trial.web.security.userdetails.CustomUserDetails;
import com.btoy.trial.web.security.util.AuthenticationUtils;
import com.btoy.trial.web.security.util.CastUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationPerRequestFilter extends OncePerRequestFilter {

    private static final String TOKEN_HEADER = "";

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = request.getHeader(TOKEN_HEADER);
            final String subject = JwtTokenProvider.extractSubject(token);
            if (JwtTokenProvider.isTokenVerified(token)) {
                Log.LOGGER.severe("Token is not valid.");
            } else if (JwtTokenProvider.isTokenExpired(token)) {
                Log.LOGGER.severe("Token expired.");
            }
            UserDetails details = userDetailsService.loadUserByUsername(subject);
            CustomUserDetails customUserDetails = CastUtils.cast(CustomUserDetails.class, details);
            if (!AuthenticationUtils.isAuthenticated()) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        customUserDetails.getUsername(),
                        customUserDetails.getPassword(),
                        customUserDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetails(request));
                AuthenticationUtils.setAuthenticationInContext(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            Log.LOGGER.severe(Arrays.toString(e.getStackTrace()));
            throw new JwtException(Arrays.toString(e.getStackTrace()));
        }
    }
}

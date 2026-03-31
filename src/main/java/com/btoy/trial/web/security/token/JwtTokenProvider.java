package com.btoy.trial.web.security.token;

import com.btoy.trial.web.security.userdetails.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.util.function.Function;

import static com.btoy.trial.constants.Log.*;

public class JwtTokenProvider {

    // TODO Change the dep. version.
    private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String buildToken(UserDetails userDetails) {
        final Date now = new Date();
        final String subject = userDetails.getUsername();
        return Jwts.builder()
                .subject(subject)
                .claims(prepareClaims(userDetails))
                .issuedAt(now)
                .expiration(new Date(now.getTime() + 1800000))
                .signWith(KEY)
                .compact();
    }

    public static boolean isTokenExpired(String token) {
        return extractExpirationTime(token).toInstant().isBefore(Instant.now());
    }

    public static boolean isTokenVerified(String token) {
        try {
            Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token);
            return true;
        } catch (IllegalArgumentException | JwtException exception) {
            LOGGER.severe(Arrays.toString(exception.getStackTrace()));
            return false;
        }
    }

    private static Map<String, Object> prepareClaims(UserDetails user) {
        final Map<String, Object> claims = new HashMap<>();
        if (user instanceof CustomUserDetails userDetails) {
            claims.put("email", userDetails.getEmail());
            claims.put("roles", userDetails.getAuthorities());
        } else if (user instanceof UserDetails userDetails) {
            final List<String> roles = CustomUserDetails.toRoles(userDetails.getAuthorities());
            claims.put("roles", roles);
        }
        return claims;
    }

    public static String extractEmail(String token) {
        final String email = extractClaims(c -> c.get("email", String.class), token);
        if (!StringUtils.hasText(email)) {
            LOGGER.severe("");
            throw new JwtException("");
        }
        return email;
    }

    public static String extractSubject(String token) {
        String subject = extractClaims(Claims::getSubject, token);
        if (Objects.isNull(subject) || subject.isEmpty()) {
            LOGGER.severe("");
            throw new JwtException("");
        }
        return subject;
    }

    private static Date extractExpirationTime(String token) {
        return extractClaims(Claims::getExpiration, token);
    }

    private static <R> R extractClaims(Function<Claims, R> claimResolver, String token) {
        Claims claims = parseToken(token);
        return claimResolver.apply(claims);
    }

    private static Claims parseToken(String token) {
        JwtParser parser = Jwts.parser().verifyWith(KEY).build();
        try {
            return parser.parseSignedClaims(token).getPayload();
        } catch (IllegalArgumentException | JwtException exception) {
            LOGGER.severe(Arrays.toString(exception.getStackTrace()));
            throw new RuntimeException(Arrays.toString(exception.getStackTrace()));
        }
    }

}

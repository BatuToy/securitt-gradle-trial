package com.btoy.trial.web.security.filterexception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.log.LogMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 * @created 23/03/2026 ~~ 22:15
 * author: batu
 */
@Component
public class PreAccessDeniedHandler implements AccessDeniedHandler {

    private static final String APPLICATION_JSON = "application/json";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        LogMessage.format("A pre controller checked handler for Access Denied Exception (FORBIDDEN) requests.");
        response.setContentType(APPLICATION_JSON);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);
        response.getWriter().write(String.format("{\"error_msg\": \"%s.\" \"cause\" : %s\"}",
                accessDeniedException.getMessage(),
                accessDeniedException.getCause())
        );
    }
}

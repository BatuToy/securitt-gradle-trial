package com.btoy.trial.web.security.filterexception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 * @created 23/03/2026 ~~ 21:43
 * author: batu
 */
@Component
public class PreAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String APPLICATION_JSON = "application/json";

    /**
     *  Check the
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        LogMessage.format("Welcome to the Custom Authentication Exception Handler. All 401 (UnAuthorized) request reach here first." +
                "What is the purpose of this class ? There is already ExceptionHandler of your Controller advice for handling all the UnAuthorized request for you dynamically." +
                "So before request reach the Controller first visits filter chain. And if the exception occurred in the servlet chain your controllerAdvice ExceptionHandler does not see the Exception that thrown.");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON);
        response.getWriter().write(
                String.format("{\"exceptionDetail:\" \"%s\", \"cause\" \"%s\"}",
                authException.getMessage(),
                authException.getCause())
        );
    }
}

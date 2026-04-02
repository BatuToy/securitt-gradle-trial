package com.btoy.trial.web.exception;

/*
 * @created 02/04/2026 ~~ 19:30
 * author: batu
 */

import com.btoy.trial.persistence.exception.PersistenceException;
import com.btoy.trial.web.response.AppResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.btoy.trial.web.security.util.ObjectUtils.fromStackTrace;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String PRODUCE_MEDIA_TYPE = MediaType.APPLICATION_JSON_VALUE;
    // Specified for only this class for identifying the exception comes from this class.
    private static final Log logger = LogFactory.getLog(GlobalExceptionHandler.class);


    @ExceptionHandler(exception = BaseException.class, produces = PRODUCE_MEDIA_TYPE)
    public @ResponseBody <T> AppResponse<T> handleInternalServerError(BaseException exception) {
        logger.error(fromStackTrace(exception.getStackTrace()), exception);
        return exception.toAppResponse();
    }

    @ExceptionHandler(exception = PersistenceException.class, produces = PRODUCE_MEDIA_TYPE)
    public @ResponseBody <T> AppResponse<T> handlePersistenceException(PersistenceException exception) {
        logger.error(fromStackTrace(exception.getStackTrace()), exception);
        return exception.toAppResponse();
    }

    @ExceptionHandler(exception = AuthenticationException.class, produces = PRODUCE_MEDIA_TYPE)
    public @ResponseStatus <T> AppResponse<T> handleAuthenticationException(CustomAuthenticationException exception) {
        logger.error(fromStackTrace(exception.getStackTrace()), exception);
        return AppResponse.of(exception.getStackTrace(),
                exception.getMessage(),
                exception.getParams(),
                HttpStatus.UNAUTHORIZED.value());
    }

    @ExceptionHandler(exception =  AccessDeniedException.class, produces = PRODUCE_MEDIA_TYPE)
    public @ResponseBody <T> AppResponse<T> handleAccessDeniedException(CustomAccessDeniedException exception) {
        logger.error(fromStackTrace(exception.getStackTrace()), exception);
        return AppResponse.of(exception.getStackTrace(),
                exception.getMessage(),
                exception.getParams(),
                HttpStatus.FORBIDDEN.value());
    }
}

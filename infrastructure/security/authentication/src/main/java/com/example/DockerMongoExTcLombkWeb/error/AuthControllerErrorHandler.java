package com.example.DockerMongoExTcLombkWeb.error;

import com.example.DockerMongoExTcLombkWeb.LogHandlerMethodExec;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@ControllerAdvice
 class AuthControllerErrorHandler {
    @LogHandlerMethodExec(
            loggerName = "AuthControllerErrorHandler",
            handlerClazz = AuthControllerErrorHandler.class,
            caughtException = AuthenticationException.class
    )

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    BadCredentialsResponseDTO handleAuthenticationException(AuthenticationException exception) {
        return new BadCredentialsResponseDTO(Collections.singletonList(exception.getMessage()));
    }

    @LogHandlerMethodExec(
            loggerName = "TokenControllerErrorHandler",
            handlerClazz = AuthControllerErrorHandler.class,
            caughtException = InternalAuthenticationServiceException.class
    )
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseBody
    BadCredentialsResponseDTO handleInternalAuthenticationServiceException(InternalAuthenticationServiceException exception) {
        return new BadCredentialsResponseDTO(Collections.singletonList(exception.getMessage()));
    }
}

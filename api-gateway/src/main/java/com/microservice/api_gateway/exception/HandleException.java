package com.microservice.api_gateway.exception;

import com.microservice.api_gateway.dto.AuthenticationResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

public class HandleException extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> handleException(CustomException ex) {
        AuthenticationResponseError authenticationResponseError = new AuthenticationResponseError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        return new ResponseEntity<>(authenticationResponseError, HttpStatus.UNAUTHORIZED);
    }

}
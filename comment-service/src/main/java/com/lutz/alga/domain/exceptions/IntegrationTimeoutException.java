package com.lutz.alga.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class IntegrationTimeoutException extends RuntimeException {
    public IntegrationTimeoutException(String message) {
        super(message);
    }
}



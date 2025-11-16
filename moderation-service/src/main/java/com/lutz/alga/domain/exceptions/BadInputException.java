package com.lutz.alga.domain.exceptions;

public class BadInputException extends RuntimeException {
    public BadInputException(String... fields) {
        super(String.join(",", fields));
    }
}

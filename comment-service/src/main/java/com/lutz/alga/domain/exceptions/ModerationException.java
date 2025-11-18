package com.lutz.alga.domain.exceptions;

public class ModerationException extends RuntimeException {
    public ModerationException(String response) {
        super(response);
    }
}

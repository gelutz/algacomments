package com.lutz.alga.api.services;

import org.springframework.stereotype.Service;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;
import com.lutz.alga.infrastructure.interfaces.Validator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModerationService {
    private final Validator validator;

    public ModerationOutput validate(@NonNull ModerationInput input) {
        log.info("Validating input ID {}: {}", input.commentId(), input.text());
        boolean valid = validator.validate(input.text());
        return new ModerationOutput(valid, "");
    }
}

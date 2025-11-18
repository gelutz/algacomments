package com.lutz.alga.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;
import com.lutz.alga.api.services.ModerationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
public class ModerationController {
    private final ModerationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ModerationOutput validate(@RequestBody ModerationInput input) {
        log.info("Receiving input ID {}: {}", input.commentId(), input.text());

        try {
            return service.validate(input);
        } catch (Exception exception) {
            log.error("Unexpected error occurred: {}", exception.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

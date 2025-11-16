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
import com.lutz.alga.domain.exceptions.BadInputException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/moderate")
@RequiredArgsConstructor
public class ModerationController {
    private final ModerationService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ModerationOutput validate(@RequestBody ModerationInput input) {
        try {
            return service.validate(input);
        } catch (BadInputException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }
    }
}

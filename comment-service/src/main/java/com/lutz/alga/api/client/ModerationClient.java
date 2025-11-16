package com.lutz.alga.api.client;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;

@HttpExchange("/api/moderate")
public interface ModerationClient {

    @PostExchange
    ModerationOutput validateText(@RequestBody ModerationInput input);
}

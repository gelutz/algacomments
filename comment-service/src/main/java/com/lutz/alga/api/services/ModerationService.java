package com.lutz.alga.api.services;

import org.springframework.stereotype.Service;

import com.lutz.alga.api.client.ModerationClient;
import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModerationService {
    private final ModerationClient moderationClient;

    public boolean validateText(ModerationInput input) {
        log.info("Sending comment {} to moderation.", input.commentId());
        log.debug("Comment data: {}", input);
        try {
            ModerationOutput output = moderationClient.validateText(input);
            return output.approved();
        } catch (Exception e) {
            log.error("Error sending comment to client \n{}", e.getMessage());
            return false;
        }
    }
}

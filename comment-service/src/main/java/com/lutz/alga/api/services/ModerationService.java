package com.lutz.alga.api.services;

import org.springframework.stereotype.Service;

import com.lutz.alga.api.client.ModerationClient;
import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;
import com.lutz.alga.domain.exceptions.IntegrationException;
import com.lutz.alga.domain.exceptions.IntegrationTimeoutException;

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
        } catch (RuntimeException e) {
            log.error("Error sending comment to client \n{}", e.getMessage());
            if (isTimeout(e)) {
                throw new IntegrationTimeoutException("Moderation service timeout");
            }
            throw new IntegrationException("Error calling moderation service");
        }
    }

    private boolean isTimeout(Throwable throwable) {
        var c = throwable.getCause();
        return c instanceof java.net.SocketTimeoutException
                || c instanceof java.net.http.HttpTimeoutException
                || c instanceof java.net.ConnectException;
    }
}

package com.lutz.alga.api.services;

import org.springframework.stereotype.Service;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;
import com.lutz.alga.domain.constants.ForbiddenWordsList;
import com.lutz.alga.domain.exceptions.BadInputException;

@Service
public class ModerationService {

    public ModerationOutput validate(ModerationInput input) {
        if (input == null) {
            throw new BadInputException("text");
        }

        boolean valid = true;

        for (String word : ForbiddenWordsList.WORDS) {
            if (input.text().matches(word)) {
                valid = false;
            }
        }

        return new ModerationOutput(valid, "");
    }
}

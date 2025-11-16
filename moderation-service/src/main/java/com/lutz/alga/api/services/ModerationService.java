package com.lutz.alga.api.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;
import com.lutz.alga.domain.constants.ForbiddenWordsList;
import com.lutz.alga.domain.exceptions.ModerationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModerationService {

    public ModerationOutput validate(ModerationInput input) {
        if (input == null) {
            throw new ModerationException();
        }

        log.info("Validating input ID {}: {}", input.commentId(), input.text());
        boolean valid = true;

        log.info(ForbiddenWordsList.WORDS.toString());
        for (String word : ForbiddenWordsList.WORDS) {
            if (!valid)
                break;

            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b");

            Matcher matcher = pattern.matcher(input.text());

            valid = !matcher.find();
            log.info("Validando para a palavra '{}'", word);
            log.info("Validou: {}", valid);
        }

        return new ModerationOutput(valid, "");
    }
}

package com.lutz.alga.api.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.lutz.alga.domain.constants.ForbiddenWordsList;
import com.lutz.alga.infrastructure.interfaces.Validator;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BadMouthValidator implements Validator {
    @SneakyThrows
    public boolean validate(@NonNull String input) {
        // Thread.sleep(Duration.ofSeconds(10));
        boolean valid = true;

        log.info(ForbiddenWordsList.WORDS.toString());
        for (String word : ForbiddenWordsList.WORDS) {
            if (!valid)
                break;

            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(word) + "\\b");

            Matcher matcher = pattern.matcher(input);

            valid = !matcher.find();
            log.info("Validando para a palavra '{}'", word);
            log.info("Validou: {}", valid);
        }

        return valid;
    }

}

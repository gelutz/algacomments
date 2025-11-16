package com.lutz.alga.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;
import com.lutz.alga.domain.constants.ForbiddenWordsList;
import com.lutz.alga.domain.exceptions.BadInputException;

public class ModerationServiceTests {
    private ModerationService sut;

    @BeforeEach
    void init() {
        sut = new ModerationService();
    }

    @Test
    void moderationServiceShouldThrowBadInputExceptionIfTextIsNull() {
        assertThrows(BadInputException.class, () -> sut.validate(null));
    }

    @Test
    void moderationServiceShouldApproveValidTextInputs() {
        ModerationInput input = new ModerationInput("texto válido", UUID.randomUUID());
        ModerationOutput output = sut.validate(input);

        assertTrue(output.approved());
        assertEquals(output.reason(), "");
    }

    @Test
    void moderationServiceShouldDisapproveInvalidTextInputs() {
        ModerationService service = new ModerationService();
        ModerationInput input = new ModerationInput(ForbiddenWordsList.WORDS.get(0), UUID.randomUUID());
        ModerationOutput output = service.validate(input);

        assertFalse(output.approved());
        assertEquals(output.reason(), "");
    }
}

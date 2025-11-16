package com.lutz.alga.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;

public class ModerationServiceTests {

    @Test
    void moderationServiceShouldApproveValidTextInputs() {
        ModerationService service = new ModerationService();
        ModerationInput input = new ModerationInput("texto válido", "Lutz");
        ModerationOutput output = service.execute(input);

        assertTrue(output.approved());
        assertEquals(output.reason(), "");
    }

    // @Test
    // void moderationServiceShouldDisapproveInvalidTextInputs() {
    // ModerationService service = new ModerationService();
    // ModerationInput input = new ModerationInput("texto inválido", "Lutz");
    // ModerationOutput output = service.execute(input);

    // assertFalse(output.approved());
    // assertEquals(output.reason(), "");
    // }
}

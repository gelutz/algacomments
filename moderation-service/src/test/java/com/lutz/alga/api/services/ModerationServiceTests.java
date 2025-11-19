package com.lutz.alga.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.dtos.ModerationOutput;
import com.lutz.alga.domain.constants.ForbiddenWordsList;
import com.lutz.alga.infrastructure.interfaces.Validator;

@ExtendWith(MockitoExtension.class)
public class ModerationServiceTests {
    private ModerationService sut;

    @Mock
    private Validator validator;

    @BeforeEach
    void init() {
        sut = new ModerationService(validator);
    }

    @Test
    void moderationServiceShouldReturnApprovedModerationOutputOnValidInput() {
        when(validator.validate(anyString())).thenReturn(true);

        ModerationInput input = new ModerationInput("texto válido", UUID.randomUUID());
        ModerationOutput output = sut.validate(input);

        assertTrue(output.approved());
        assertEquals(output.reason(), "");
    }

    @Test
    void moderationServiceShouldReturnDisapprovedModerationOutputOnInvalidInput() {
        when(validator.validate(anyString())).thenReturn(false);
        ModerationInput input = new ModerationInput(ForbiddenWordsList.WORDS.get(0), UUID.randomUUID());
        ModerationOutput output = sut.validate(input);

        assertFalse(output.approved());
        assertEquals(output.reason(), "");
    }
}

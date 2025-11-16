package com.lutz.alga.api.dtos;

import java.util.UUID;

public record ModerationInput(String text, UUID commentId) {

}

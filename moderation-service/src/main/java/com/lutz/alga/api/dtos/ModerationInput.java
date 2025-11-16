package com.lutz.alga.api.dtos;

import java.util.UUID;

import io.micrometer.common.lang.NonNull;

public record ModerationInput(@NonNull String text, UUID commentId) {
}
package com.lutz.alga.api.dtos;

import io.micrometer.common.lang.NonNull;

public record ModerationInput(@NonNull String text, @NonNull String author) {
}

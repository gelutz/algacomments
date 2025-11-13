package com.lutz.alga.api.dtos;

import com.lutz.alga.domain.models.Comment;

import io.micrometer.common.lang.NonNull;

public record CommentInput(@NonNull String text, @NonNull String author) {

    public Comment toModel() {
        throw new UnsupportedOperationException("Unimplemented method 'toModel'");
    }
}

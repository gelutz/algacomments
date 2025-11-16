package com.lutz.alga.api.dtos;

import java.time.OffsetDateTime;

import com.lutz.alga.domain.exceptions.BadInputException;
import com.lutz.alga.domain.models.Comment;
import com.lutz.alga.shared.IDUtils;

import io.micrometer.common.lang.NonNull;

public record CommentInput(@NonNull String text, @NonNull String author) {
    public Comment toModel() {
        if (text == null || author == null) {
            throw new BadInputException("text", "author");
        }
        return Comment.builder()
                .id(IDUtils.generateUUID())
                .text(text())
                .author(author())
                .createdAt(OffsetDateTime.now())
                .build();
    }
}

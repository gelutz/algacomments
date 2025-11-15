package com.lutz.alga.api.dtos;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.lutz.alga.domain.models.Comment;

import io.micrometer.common.lang.NonNull;

public record CommentOutput(UUID id, String text, String author, OffsetDateTime createdAt) {
    public static CommentOutput fromModel(@NonNull Comment comment) {
        return new CommentOutput(
                comment.getId(),
                comment.getText(),
                comment.getAuthor(),
                comment.getCreatedAt());
    }
}

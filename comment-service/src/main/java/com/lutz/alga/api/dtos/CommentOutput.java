package com.lutz.alga.api.dtos;

import java.time.OffsetDateTime;

public record CommentOutput(Long id, String text, String author, OffsetDateTime createdAt) {
}

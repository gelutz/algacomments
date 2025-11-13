package com.lutz.alga.domain.models;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Comment {
    @Id
    public UUID id;
    public String author;
    public String text;
    public OffsetDateTime createdAt;
}

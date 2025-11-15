package com.lutz.alga.api.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lutz.alga.api.dtos.CommentInput;
import com.lutz.alga.api.repositories.CommentRepository;
import com.lutz.alga.domain.models.Comment;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment create(@NonNull CommentInput input) {
        return commentRepository.save(input.toModel());
    }

    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Comment findById(UUID id) {
        return commentRepository.findById(id).orElse(null);
    }
}

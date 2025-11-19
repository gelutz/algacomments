package com.lutz.alga.api.services;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lutz.alga.api.dtos.CommentInput;
import com.lutz.alga.api.dtos.ModerationInput;
import com.lutz.alga.api.repositories.CommentRepository;
import com.lutz.alga.domain.exceptions.BadInputException;
import com.lutz.alga.domain.exceptions.ModerationException;
import com.lutz.alga.domain.models.Comment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ModerationService moderationService;

    public Comment create(CommentInput input) {
        if (input.text() == null || input.author() == null) {
            throw new BadInputException("Neither text nor author can be null.");
        }

        Comment comment = input.toModel();
        if (!moderationService.validateText(new ModerationInput(comment.getText(), comment.getId()))) {
            throw new ModerationException(comment.getText());
        }

        return commentRepository.save(comment);
    }

    public Page<Comment> findAll(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Comment findById(UUID id) {
        return commentRepository.findById(id).orElse(null);
    }
}

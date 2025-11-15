package com.lutz.alga.api.controllers;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lutz.alga.api.dtos.CommentInput;
import com.lutz.alga.api.dtos.CommentOutput;
import com.lutz.alga.api.services.CommentService;
import com.lutz.alga.domain.models.Comment;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public Page<Comment> get(@PageableDefault Pageable pageable) {
        return commentService.findAll(pageable);
    }

    @GetMapping("{commentId}")
    public CommentOutput find(@PathVariable UUID commentId) {
        return CommentOutput.fromModel(commentService.findById(commentId));
    }

    @PostMapping
    public CommentOutput create(CommentInput input) {
        Comment comment = commentService.create(input);
        return CommentOutput.fromModel(comment);
    }
}

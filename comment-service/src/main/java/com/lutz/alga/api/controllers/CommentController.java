package com.lutz.alga.api.controllers;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lutz.alga.api.dtos.CommentInput;
import com.lutz.alga.api.dtos.CommentOutput;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    @GetMapping
    public CommentOutput get(@PageableDefault Pageable pageable) {
        // return commentRepository.findAll(pageable);
        return null;
    }

    @GetMapping("{commentId}")
    public CommentOutput find(@PathVariable UUID commentId) {
        // return commentRepository.findById(commentId);
        return null;
    }

    @PostMapping
    public CommentOutput create(CommentInput input) {
        return null;
        // Comment comment = input.toModel();
        // commentRepository.save(comment)
        // return CommentOutput.from(comment);
    }
}

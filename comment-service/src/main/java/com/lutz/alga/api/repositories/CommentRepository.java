package com.lutz.alga.api.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lutz.alga.domain.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

}

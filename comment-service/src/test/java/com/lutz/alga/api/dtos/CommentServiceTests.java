package com.lutz.alga.api.dtos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.lutz.alga.api.repositories.CommentRepository;
import com.lutz.alga.api.services.CommentService;
import com.lutz.alga.domain.models.Comment;
import com.lutz.alga.shared.IDUtils;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTests {
    private UUID idStub = IDUtils.generateUUID();

    private CommentService sut;
    @Mock
    private CommentRepository commentRepository;

    @BeforeEach
    void init() {
        sut = new CommentService(commentRepository);
    }

    @Test
    @DisplayName("CommentService::findById should return null when an unknown ID is used")
    void commentServiceShouldReturnNullWhenNoCommentIsFound() {
        UUID differentId = IDUtils.generateUUID();
        when(commentRepository.findById(differentId)).thenReturn(Optional.empty());

        Comment comment = sut.findById(differentId);
        assertNull(comment);
    }

    @Test
    @DisplayName("CommentService::findById should find specific Comment by it's ID")
    void commentServiceShouldFindComment() {
        Comment mockedComment = mock(Comment.class);
        when(mockedComment.getId()).thenReturn(idStub);

        when(commentRepository.findById(idStub)).thenReturn(Optional.of(mockedComment));

        Comment comment = sut.findById(idStub);
        assertNotNull(comment);
        assertEquals(mockedComment.getId(), comment.getId());
    }

    @Test
    @DisplayName("CommentService::findAll should receive a pageable and return a Page with the items")
    void commentServiceShouldReturnPagedContent() {
        Comment mockedComment1 = mock(Comment.class);
        Comment mockedComment2 = mock(Comment.class);

        List<Comment> stub = List.of(mockedComment1, mockedComment2);
        Pageable pageable = Pageable.ofSize(2);
        when(commentRepository.findAll(pageable)).thenReturn(new PageImpl<>(stub));

        Page<Comment> comments = sut.findAll(pageable);
        assertEquals(stub.size(), comments.getContent().size());
    }

    @Test
    @DisplayName("CommentService::create should use CommentRepository::save and create a new comment with the given CommentInput")
    void commentServiceShouldUseCommentRepositoryToCreateComments() {
        Comment mockedComment = mock(Comment.class);
        when(mockedComment.getId()).thenReturn(idStub);

        CommentInput mockedInput = mock(CommentInput.class);
        when(mockedInput.toModel()).thenReturn(mockedComment);

        when(commentRepository.save(mockedComment)).thenReturn(mockedComment);

        Comment comment = sut.create(mockedInput);
        assertEquals(mockedComment.getId(), comment.getId());
    }
}
package com.lutz.alga.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

import com.lutz.alga.api.dtos.CommentInput;
import com.lutz.alga.api.dtos.CommentOutput;
import com.lutz.alga.domain.models.Comment;
import com.lutz.alga.shared.IDUtils;

public class CommentInputAndOutputTests {
	private String textoStub = "Texto teste";
	private String authorStub = "Gabriel Lutz";

	@Test
	void commentInputShouldConvertToComment() {
		CommentInput sut = new CommentInput(textoStub, authorStub);
		Comment converted = sut.toModel();

		assertNotNull(converted.getId());
		assertNotNull(converted.getCreatedAt());
		assertEquals(sut.text(), converted.getText());
		assertEquals(sut.author(), converted.getAuthor());
	}

	@Test
	void createCommentOutputFromComment() {
		Comment comment = Comment.builder()
				.id(IDUtils.generateUUID())
				.text(textoStub)
				.author(authorStub)
				.createdAt(OffsetDateTime.now())
				.build();
		CommentOutput co = CommentOutput.fromModel(comment);

		assertNotNull(co.id());
		assertNotNull(co.createdAt());
		assertEquals(co.text(), comment.getText());
		assertEquals(co.author(), comment.getAuthor());
	}
}

package com.lutz.alga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.lutz.alga.api.dtos.CommentInput;
import com.lutz.alga.domain.models.Comment;

@SpringBootTest
class CommentsApplicationTests {

	// sut = service under test
	private CommentInput sut;

	@BeforeEach
	void loadStubs() {
		String textoStub = "Texto teste";
		String authorStub = "Gabriel Lutz";
		sut = new CommentInput(textoStub, authorStub);
	}

	@Test
	void commentInputShouldConvertToComment() {
		Comment converted = sut.toModel();

		assertNotNull(converted.getId());
		assertNotNull(converted.getCreatedAt());
		assertEquals(sut.text(), converted.getText());
		assertEquals(sut.author(), converted.getAuthor());
	}
}

package ru.qa.assertions;

import ru.qa.dto.PostDto;

import static org.junit.jupiter.api.Assertions.*;

public final class PostDtoAssertions {

    private PostDtoAssertions() {
        throw new AssertionError("Utility class");
    }

    /**
     * Проверяет что все обязательные поля PostDto заполнены.
     */
    public static void assertValidPost(PostDto post) {
        assertAll(
                () -> assertNotNull(post.getId(), "ID should not be null"),
                () -> assertNotNull(post.getUserId(), "UserID should not be null"),
                () -> assertNotNull(post.getTitle(), "Title should not be null"),
                () -> assertFalse(post.getTitle().isEmpty(), "Title should not be empty"),
                () -> assertNotNull(post.getBody(), "Body should not be null"),
                () -> assertFalse(post.getBody().isEmpty(), "Body should not be empty")
                 );
    }

    /**
     * Проверяет что пост принадлежит указанному пользователю.
     */
    public static void assertUserPost(PostDto post, int expectedUserId) {
        assertEquals(expectedUserId, post.getUserId(),
                     "Post should belong to user " + expectedUserId);
    }
}
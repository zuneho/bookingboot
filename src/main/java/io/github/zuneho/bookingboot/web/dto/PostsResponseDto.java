package io.github.zuneho.bookingboot.web.dto;

import io.github.zuneho.bookingboot.domain.posts.Posts;
import lombok.Getter;


@Getter
public class PostsResponseDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String author;

    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.content = posts.getContent();
        this.author = posts.getAuthor();
    }
}

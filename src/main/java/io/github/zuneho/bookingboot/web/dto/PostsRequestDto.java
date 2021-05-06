package io.github.zuneho.bookingboot.web.dto;

import io.github.zuneho.bookingboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsRequestDto {
    private final String title;
    private final String content;
    private final String author;

    @Builder
    public PostsRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}

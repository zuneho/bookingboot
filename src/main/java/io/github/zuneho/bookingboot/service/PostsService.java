package io.github.zuneho.bookingboot.service;

import io.github.zuneho.bookingboot.domain.posts.Posts;
import io.github.zuneho.bookingboot.domain.posts.PostsRepository;
import io.github.zuneho.bookingboot.web.dto.PostsResponseDto;
import io.github.zuneho.bookingboot.web.dto.PostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity())
                .getId();
    }

    @Transactional
    public Long update(Long id, PostsRequestDto requestDto) {
        Posts posts = getById(id);
        posts.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getAuthor());

        postsRepository.save(posts);
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = getById(id);
        return new PostsResponseDto(posts);
    }

    private Posts getById(Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 데이터가 존재하지 않습니다."));
    }
}

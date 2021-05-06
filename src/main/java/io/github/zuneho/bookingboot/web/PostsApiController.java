package io.github.zuneho.bookingboot.web;

import io.github.zuneho.bookingboot.service.PostsService;
import io.github.zuneho.bookingboot.web.dto.PostsResponseDto;
import io.github.zuneho.bookingboot.web.dto.PostsRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto get(@PathVariable Long id ){
        return postsService.findById(id);
    }

    @PostMapping("/api/v1/posts")
    public ResponseEntity<Long> save(@RequestBody PostsRequestDto requestDto) {
        return ResponseEntity.ok(postsService.save(requestDto));
    }

    @PutMapping("/api/v1/posts/{id}")
    public ResponseEntity<Long> update(@RequestBody PostsRequestDto requestDto,
                                       @PathVariable Long id) {
        return ResponseEntity.ok(postsService.update(id, requestDto));
    }
}

package io.github.zuneho.bookingboot.web;

import io.github.zuneho.bookingboot.domain.posts.Posts;
import io.github.zuneho.bookingboot.domain.posts.PostsRepository;
import io.github.zuneho.bookingboot.web.dto.PostsRequestDto;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
    @LocalServerPort
    private  int port;

    @Autowired
    private  TestRestTemplate testRestTemplate;

    @Autowired
    private  PostsRepository postsRepository;

    @After
    public void clean() {
        postsRepository.deleteAll();
    }

    @Test
    public void test_ok_save_posts() {
        //given
        String title = "testPosts";
        String content = "testContent";
        String author = "testAuthor";
        PostsRequestDto requestDto = PostsRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
        String targetUrl = "http://localhost:" + port + "/api/v1/posts";

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(targetUrl, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = postsRepository.findAll();
        Posts testSavedPosts = postsList.get(0);

        assertThat(testSavedPosts.getId()).isEqualTo(responseEntity.getBody());
        assertThat(testSavedPosts.getTitle()).isEqualTo(title);
        assertThat(testSavedPosts.getContent()).isEqualTo(content);
        assertThat(testSavedPosts.getAuthor()).isEqualTo(author);
    }

    @Test
    public void test_ok_update() {
        //given
        String title = "testPosts";
        String content = "testContent";
        String author = "testAuthor";

        PostsRequestDto requestDto = PostsRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        Posts savedPosts = postsRepository.save(requestDto.toEntity());


        Long lastId = savedPosts.getId();
        String targetUrl = "http://localhost:" + port + "/api/v1/posts/" + lastId;

        String updateTitle = "testPosts1";
        String updateContent = "testContent1";
        PostsRequestDto updateRequest = PostsRequestDto.builder()
                .title(updateTitle)
                .content(updateContent)
                .author(author)
                .build();

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(
                targetUrl,
                HttpMethod.PUT,
                new HttpEntity<>(updateRequest),
                Long.class
        );

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(lastId);

        List<Posts> postsList = postsRepository.findAll();
        Posts testUpdatedPosts = postsList.get(0);

        assertThat(testUpdatedPosts.getId()).isEqualTo(responseEntity.getBody());
        assertThat(testUpdatedPosts.getTitle()).isEqualTo(updateTitle);
        assertThat(testUpdatedPosts.getContent()).isEqualTo(updateContent);
        assertThat(testUpdatedPosts.getAuthor()).isEqualTo(author);
    }
}
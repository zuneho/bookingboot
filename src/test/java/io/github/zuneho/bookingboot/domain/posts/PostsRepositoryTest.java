package io.github.zuneho.bookingboot.domain.posts;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    private final PostsRepository postsRepository;

    @Autowired
    public PostsRepositoryTest(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @After
    public void clean() {
        postsRepository.deleteAll();
    }

    @Test
    public void test_ok_default_save() {
        Posts newPosts = Posts.builder()
                .title("제목")
                .content("내용")
                .author("저자")
                .build();


        postsRepository.save(newPosts);

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo("제목");
        assertThat(posts.getContent()).isEqualTo("내용");
    }

}
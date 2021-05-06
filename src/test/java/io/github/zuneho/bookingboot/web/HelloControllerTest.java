package io.github.zuneho.bookingboot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@WebMvcTest
public class HelloControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public HelloControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void test_ok_hello() throws Exception {
        String hello = "hello";

        mockMvc.perform(get("/hello")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void test_ok_helloDto() throws Exception {
        String name = "name1";
        int amount = 1000;

        mockMvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
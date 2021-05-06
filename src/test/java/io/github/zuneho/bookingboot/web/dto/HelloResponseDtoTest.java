package io.github.zuneho.bookingboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class HelloResponseDtoTest {

    @Test
    public void test_ok_lombok_response() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto responseDto = new HelloResponseDto(name, amount);

        //then
        assertThat(responseDto.getName()).isEqualTo(name);
        assertThat(responseDto.getAmount()).isEqualTo(amount);
    }
}
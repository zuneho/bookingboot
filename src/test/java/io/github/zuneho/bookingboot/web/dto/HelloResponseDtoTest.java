package io.github.zuneho.bookingboot.web.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class HelloResponseDtoTest {

    @Test
    public void test_ok_lombok_response() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto responseDto = new HelloResponseDto(name, amount);

        //then
        assertEquals(name, responseDto.getName());
        assertEquals(amount, responseDto.getAmount());
    }
}
package com.example.testcode.todo.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class ResponseTodoDto {
    private int id;
    private String name;
}

package com.example.testcode.todo.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class RequestTodoDto {
    private String name;
}

package com.example.testcode.todo.controller;

import com.example.testcode.todo.dto.response.ResponseTodoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class TodoApiController {
    @GetMapping("/api/todo")
    public ResponseEntity<String> todoGet() {
        log.info("[GET] /api/todo");

        return new ResponseEntity<>("todo", HttpStatus.OK);
    }

    @PostMapping("/api/todo")
    public ResponseEntity<ResponseTodoDto> todoPost(@RequestBody Map<String, Object> param) {
        log.info("[POST] /api/todo");
        log.info("param > " +param);

        ResponseTodoDto dto = ResponseTodoDto.builder()
                                                .id((Integer)param.get("id"))
                                                .name((String) param.get("name"))
                                                    .build();

        log.info("dto > " +dto);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}

package com.example.testcode.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// junit5 : RunWith() => ExtendWith()
// ()안에 있는 스프링 실행자를 사용하겠다는 의미. -> 스프링부트 테스트와 JUnit 사이에 연결자 역할
@ExtendWith(SpringExtension.class)

// Web에서 테스트하기 힘든 컨트롤러를 테스트 해준다.
// Web(MVC)에 집중할 수 있는 어노테이션으로 @Controller, @ControllerAdvice등을 사용할 수 있다.
@WebMvcTest(controllers = TodoApiController.class)
class TodoApiControllerTest {
    
    // Controller
    // 클라이언트의 Request를 잘받아서 Server에 넘겨주고 Server의 Response를 클라이언트에게 Return
    // 위 역할에 충실하게 Test 코드 작성 필요
    
    @Autowired
    private MockMvc mockMvc;
    // 웹 어플리케이션을 서버에 배포하지 않고도 스프링 MVC의 동작을 재현할 수 있는 클래스
    // 이 클래스(객체)를 통해 웹 API를 테스트할 수 있다.(HTTP, GET, POST 등)

    @Autowired
    private ObjectMapper objectMapper;
    // ObjectMapper : json parsing응 위한 객체
    // objectMapper.readValue(string, Object.class); String(json) -> Object
    // objectMapper.writeValueAsString(object) : Object -> String(json type) : @RequestBody == JSON.stringify()

    /* @Test
    @DisplayName("")
    public todo_테스트() {
        // given : ~ 이 주어지고
        // when :  ~ 이것을 실행했을때
        // then : ~ 결과가 이것이 나와야 된다.
    } */

    @Test
    @DisplayName("TODO 리턴")
    public void GET_Todo리턴_테스트() throws Exception {
        // given : ~ 이 주어지고
        String todo = "todo";
        String api = "/api/todo";

        // when :  ~ 이것을 실행했을때
        final ResultActions actions = mockMvc.perform(get(api));           // get 요청 => /api/todo, HTTP 메소드를 결정 get(), post(), put(), delete()

        // then : ~ 결과가 이것이 나와야 된다.
        actions
            .andExpect(status().isOk())  // 위 요청 결과에 따라 상태는 200
            // andExpect() : 응답을 검증하는 역할
            // status() : 상태 코드
            //  ㄴ isOk() : 200
            //  ㄴ isNotFound() : 404
            //  ㄴ isMethodNotAllowed() : 405
            //  ㄴ isInternalServerError() : 500

            .andExpect(content().string(todo)) // response body에 todo가 있는지 검증
            // content() : 응답 정보 검증

            .andDo(print());    // 요청/응답 전체 메세지를 확인
    }

    @Test
    @DisplayName("ResponseTodoDto 리턴")
    public void POST_ResponseTodoDto리턴_테스트() throws Exception {
        // given : ~ 이 주어지고
        String api = "/api/todo";
        int id = 1;
        String name = "name";

        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("name", name);

        String jsonRequest = objectMapper.writeValueAsString(param);    // writeValueAsString : Object -> String(json type) : @RequestBody == JSON.stringify()

        // when :  ~ 이것을 실행했을때
        final ResultActions actions = mockMvc.perform(
                                                post(api)     // post 요청 => /api/todo
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .accept(MediaType.APPLICATION_JSON)
                                                    .characterEncoding("UTF-8")
                                                    .content(jsonRequest));

        // then : ~ 결과가 이것이 나와야 된다.
        actions
            .andExpect(status().isOk()) // 위 요청 결과에 따라 상태는 200
            .andExpect(jsonPath("id").value(id))        // ResponseBody json Object에 포함되어 있는지 확인
            .andExpect(jsonPath("name").value(name))
            .andDo(print());
    }
}
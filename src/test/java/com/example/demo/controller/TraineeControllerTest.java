package com.example.demo.controller;

import com.example.demo.JUnitWebAppTest;
import com.example.demo.request.TraineeCreateRequest;
import com.example.demo.response.TraineeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JUnitWebAppTest
public class TraineeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_given_one_name_when_add_one_student() throws Exception {

        String url = "/trainees";

        TraineeCreateRequest traineeCreateRequest = TraineeCreateRequest.builder()
                .name("张三")
                .office("背景")
                .email("zhangsan@b.com")
                .github("zhangsan")
                .zoomId("zhangsan")
                .build();

        String contentAsString = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(traineeCreateRequest))
                .contentType("application/json;charset=UTF-8")
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        TraineeResponse traineeResponse = objectMapper.readValue(contentAsString, TraineeResponse.class);

        assertEquals(traineeCreateRequest.getName(), traineeResponse.getName());
        assertEquals(traineeCreateRequest.getOffice(), traineeResponse.getOffice());
    }

}
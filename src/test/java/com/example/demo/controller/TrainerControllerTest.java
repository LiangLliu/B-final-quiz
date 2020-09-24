package com.example.demo.controller;

import com.example.demo.JUnitWebAppTest;
import com.example.demo.request.TrainerCreateRequest;
import com.example.demo.response.TrainerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JUnitWebAppTest
public class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String url = "/trainers";

    @Test
    public void should_given_one_name_when_add_one_trainee() throws Exception {

        TrainerCreateRequest trainerCreateRequest = TrainerCreateRequest.builder()
                .name("张三")
                .build();

        String contentAsString = mockMvc.perform(post(url)
                .content(objectMapper.writeValueAsString(trainerCreateRequest))
                .contentType("application/json;charset=UTF-8")
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        TrainerResponse trainerResponse = objectMapper.readValue(contentAsString, TrainerResponse.class);

        assertEquals(trainerCreateRequest.getName(), trainerResponse.getName());

    }
}
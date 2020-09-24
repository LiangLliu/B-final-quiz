package com.example.demo.controller;

import com.example.demo.JUnitWebAppTest;
import com.example.demo.controller.dto.TraineeCreateRequest;
import com.example.demo.controller.dto.TrainerCreateRequest;
import com.example.demo.controller.dto.TrainerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JUnitWebAppTest
@ActiveProfiles("test")
public class TrainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String url = "/trainers";

    @Nested
    public class CreateTrainerTest {


        private TrainerCreateRequest trainerCreateRequest;

        @BeforeEach
        public void init() {
            trainerCreateRequest = TrainerCreateRequest.builder()
                    .name("张三")
                    .build();
        }

        @Test
        public void should_given_one_name_when_add_one_trainer() throws Exception {

            String contentAsString = mockMvc.perform(post(url)
                    .content(objectMapper.writeValueAsString(trainerCreateRequest))
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            TrainerResponse trainerResponse = objectMapper.readValue(contentAsString, TrainerResponse.class);

            assertEquals(trainerCreateRequest.getName(), trainerResponse.getName());
        }

        @Test
        public void should_return_400_when_given_name_is_null() throws Exception {
            trainerCreateRequest.setName(null);
            createTrainerErrorTest(trainerCreateRequest);
        }

        private void createTrainerErrorTest(TrainerCreateRequest trainerCreateRequest) throws Exception {

            mockMvc.perform(post(url)
                    .content(objectMapper.writeValueAsString(trainerCreateRequest))
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isBadRequest());
        }

    }


    @Nested
    public class DeleteTrainerTest {
        @Test
        public void should_delete_one_trainer_when_delete_trainer_and_given_one_trainer_exist_id() throws Exception {

            mockMvc.perform(delete(url + "/3"))
                    .andExpect(status().isNoContent());
        }

        @Test
        public void should_return_404_when_delete_trainer_is_is_not_exist() throws Exception {

            mockMvc.perform(delete(url + "/0"))
                    .andExpect(status().isNotFound());
        }

    }

}
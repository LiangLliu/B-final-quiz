package com.example.demo.controller;

import com.example.demo.JUnitWebAppTest;
import com.example.demo.controller.dto.TraineeCreateRequest;
import com.example.demo.controller.dto.TraineeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JUnitWebAppTest
public class TraineeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String url = "/trainees";


    @Nested
    public class CreateTraineeTest {

        private TraineeCreateRequest traineeCreateRequest;

        @BeforeEach
        public void init() {
            traineeCreateRequest = TraineeCreateRequest.builder()
                    .name("张三")
                    .office("背景")
                    .email("zhangsan@b.com")
                    .github("zhangsan")
                    .zoomId("zhangsan")
                    .build();
        }

        @Test
        public void should_given_one_name_when_add_one_trainee() throws Exception {

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

        @Test
        public void should_return_400_when_given_name_is_null() throws Exception {
            traineeCreateRequest.setName(null);
            createTraineeErrorTest(traineeCreateRequest);
        }

        @Test
        public void should_return_400_when_given_github_is_null() throws Exception {
            traineeCreateRequest.setGithub(null);
            createTraineeErrorTest(traineeCreateRequest);
        }

        @Test
        public void should_return_400_when_given_office_is_null() throws Exception {
            traineeCreateRequest.setOffice(null);
            createTraineeErrorTest(traineeCreateRequest);
        }

        @Test
        public void should_return_400_when_given_zoom_is_null() throws Exception {
            traineeCreateRequest.setZoomId(null);
            createTraineeErrorTest(traineeCreateRequest);
        }

        @Test
        public void should_return_400_when_given_email_is_null() throws Exception {
            traineeCreateRequest.setEmail(null);
            createTraineeErrorTest(traineeCreateRequest);
        }

        @Test
        public void should_return_400_when_given_email_is_error() throws Exception {
            traineeCreateRequest.setEmail("test");
            createTraineeErrorTest(traineeCreateRequest);
        }

        private void createTraineeErrorTest(TraineeCreateRequest traineeCreateRequest) throws Exception {

            mockMvc.perform(post(url)
                    .content(objectMapper.writeValueAsString(traineeCreateRequest))
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isBadRequest());
        }

    }


    @Test
    public void should_get_all_trainee_when_given_get_trainee_list_and_grouped_is_false() throws Exception {

        mockMvc.perform(get(url + "?grouped=false")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("沈乐棋"))
                .andExpect(jsonPath("$[0].email").value("shenleqi@gtb.com"));

    }

    @Test
    public void should_delete_one_trainee_when_delete_trainee_and_given_one_trainee_exist_id() throws Exception {

        mockMvc.perform(delete(url + "/3"))
                .andExpect(status().isNoContent());
    }

}
package com.example.demo.controller;

import com.example.demo.JUnitWebAppTest;
import com.example.demo.controller.dto.GroupModifyRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JUnitWebAppTest
public class GroupsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String url = "/groups";

    @Test
    public void should_auto_grouping_when_need_grouping() throws Exception {

        mockMvc.perform(post(url + "/auto-grouping")
                .contentType("application/json;charset=UTF-8")
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk());

    }

    @Test
    public void should_get_all_groups_when_get_groups() throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }


    @Nested
    public class ModifyGroupNameTest {

        private GroupModifyRequest groupModifyRequest;

        @BeforeEach
        public void init() throws Exception {
            groupModifyRequest = GroupModifyRequest.builder()
                    .name("name")
                    .build();

            mockMvc.perform(post(url + "/auto-grouping")
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isOk());
        }

        @Test
        public void should_modify_groups_name_when_get_groups() throws Exception {

            mockMvc.perform(patch(url + "/1")
                    .content(objectMapper.writeValueAsString(groupModifyRequest))
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isOk());
        }


        @Test
        public void should_return_404_when_group_is_is_not_exist() throws Exception {

            groupModifyRequest.setName("test");

            mockMvc.perform(patch(url + "/0")
                    .content(objectMapper.writeValueAsString(groupModifyRequest))
                    .contentType("application/json;charset=UTF-8")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().isNotFound());
        }

    }


}
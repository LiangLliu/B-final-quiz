package com.example.demo.controller;

import com.example.demo.JUnitWebAppTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@JUnitWebAppTest
public class GroupsControllerTest {

    @Autowired
    private MockMvc mockMvc;


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

}
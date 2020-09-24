package com.example.demo.controller;

import com.example.demo.controller.dto.GroupsResponse;
import com.example.demo.service.GroupsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupsController {

    private final GroupsService groupsService;

    public GroupsController(GroupsService groupsService) {
        this.groupsService = groupsService;
    }

    @PostMapping("/auto-grouping")
    public List<GroupsResponse> autoGrouping() {

        return groupsService.autoGrouping();
    }


}

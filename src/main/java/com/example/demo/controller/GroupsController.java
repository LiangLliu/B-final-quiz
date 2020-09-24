package com.example.demo.controller;

import com.example.demo.controller.dto.GroupModifyRequest;
import com.example.demo.controller.dto.GroupsResponse;
import com.example.demo.service.GroupsService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public List<GroupsResponse> getAllGroup() {
        return groupsService.getAllGroup();
    }

    @PatchMapping("/{groupId}")
    public void modifyGroupName(@PathVariable("groupId") long groupId,
                                @RequestBody GroupModifyRequest request) {

        groupsService.modifyGroupName(groupId, request);
    }

}

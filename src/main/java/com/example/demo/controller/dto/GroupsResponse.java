package com.example.demo.controller.dto;

import com.example.demo.service.domain.Groups;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupsResponse {
    private Long id;

    private String name;

    private List<TrainerResponse> trainers;

    private List<TraineeResponse> trainees;

    public static List<GroupsResponse> fromGroups(List<Groups> groupsList) {
        return groupsList.stream()
                .map(GroupsResponse::fromGroups)
                .collect(Collectors.toList());
    }


    public static GroupsResponse fromGroups(Groups groupsEntity) {

        return GroupsResponse.builder()
                .id(groupsEntity.getRanking())
                .name(groupsEntity.getName())
                .trainers(TrainerResponse.fromTrainer(groupsEntity.getTrainers()))
                .trainees(TraineeResponse.fromTrainee(groupsEntity.getTrainees()))
                .build();
    }
}

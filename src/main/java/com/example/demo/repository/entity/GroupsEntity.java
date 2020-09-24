package com.example.demo.repository.entity;

import com.example.demo.service.domain.Groups;
import com.example.demo.service.domain.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class GroupsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long Ranking;

    @OneToMany(mappedBy = "groupsId")
    private List<TrainerEntity> trainers;

    @OneToMany(mappedBy = "groupsId")
    private List<TraineeEntity> trainees;

    public static List<GroupsEntity> fromGroups(List<Groups> groupsList) {

        return groupsList.stream()
                .map(GroupsEntity::fromGroups)
                .collect(Collectors.toList());
    }


    public static GroupsEntity fromGroups(Groups groups) {

        GroupsEntity groupsEntity = GroupsEntity.builder()
                .id(groups.getId())
                .Ranking(groups.getRanking())
                .name(groups.getName())
                .trainers(TrainerEntity.fromTrainer(groups.getTrainers()))
                .trainees(TraineeEntity.fromTrainee(groups.getTrainees()))
                .build();

        groupsEntity.setTrainers(groupsEntity.getTrainers().stream()
                .peek(it -> it.setGroups(groupsEntity)).collect(Collectors.toList()));

        groupsEntity.setTrainees(groupsEntity.getTrainees().stream()
                .peek(it -> it.setGroups(groupsEntity)).collect(Collectors.toList()));

        return groupsEntity;
    }

    public static List<Groups> toGroups(List<GroupsEntity> groupsEntities) {
        return groupsEntities.stream()
                .map(GroupsEntity::toGroups)
                .collect(Collectors.toList());
    }

    public static Groups toGroups(GroupsEntity groupsEntity) {

        return Groups.builder()
                .id(groupsEntity.getId())
                .Ranking(groupsEntity.getRanking())
                .name(groupsEntity.getName())
                .trainers(TrainerEntity.toTrainer(groupsEntity.getTrainers()))
                .trainees(TraineeEntity.toTrainee(groupsEntity.getTrainees()))
                .build();
    }
}

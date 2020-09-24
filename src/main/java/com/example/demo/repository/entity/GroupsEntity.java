package com.example.demo.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    private Integer Ranking;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "groupsId")
    private List<TrainerEntity> trainers;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "groupsId")
    private List<TraineeEntity> trainees;
}

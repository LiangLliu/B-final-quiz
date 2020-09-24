package com.example.demo.repository.entity;

import com.example.demo.service.domain.Trainee;
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
@Table(name = "trainee")
public class TraineeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String office;

    private String email;

    private String github;

    private String zoomId;

    @Column(name = "groups_id")
    private Long groupsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id", insertable = false, updatable = false)
    private GroupsEntity groups;

    public static TraineeEntity fromTrainee(Trainee trainee) {
        return TraineeEntity.builder()
                .id(trainee.getId())
                .name(trainee.getName())
                .github(trainee.getGithub())
                .office(trainee.getOffice())
                .zoomId(trainee.getZoomId())
                .email(trainee.getEmail())
                .build();
    }

    public static Trainee toTrainee(TraineeEntity traineeEntity) {
        return Trainee.builder()
                .id(traineeEntity.getId())
                .name(traineeEntity.getName())
                .github(traineeEntity.getGithub())
                .office(traineeEntity.getOffice())
                .zoomId(traineeEntity.getZoomId())
                .email(traineeEntity.getEmail())
                .build();
    }

    public static List<Trainee> toTrainee(List<TraineeEntity> traineeEntities) {
        return traineeEntities.stream()
                .map(TraineeEntity::toTrainee)
                .collect(Collectors.toList());
    }

    public static List<TraineeEntity> fromTrainee(List<Trainee> trainees) {
        return trainees.stream()
                .map(TraineeEntity::fromTrainee)
                .collect(Collectors.toList());
    }
}

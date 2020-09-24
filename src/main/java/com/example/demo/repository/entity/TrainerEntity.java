package com.example.demo.repository.entity;

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
@Table(name = "trainer")
public class TrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "groups_id")
    private Long groupsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id", insertable = false, updatable = false)
    private GroupsEntity groups;

    public static TrainerEntity fromTrainer(Trainer trainer) {
        return TrainerEntity.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .build();
    }

    public static Trainer toTrainer(TrainerEntity trainerEntity) {
        return Trainer.builder()
                .id(trainerEntity.getId())
                .name(trainerEntity.getName())
                .build();
    }


    public static List<Trainer> toTrainer(List<TrainerEntity> trainerEntities) {
        return trainerEntities.stream()
                .map(TrainerEntity::toTrainer)
                .collect(Collectors.toList());

    }

    public static List<TrainerEntity> fromTrainer(List<Trainer> trainers) {
        return trainers.stream()
                .map(TrainerEntity::fromTrainer)
                .collect(Collectors.toList());
    }
}

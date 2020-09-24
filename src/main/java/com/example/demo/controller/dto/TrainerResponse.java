package com.example.demo.controller.dto;

import com.example.demo.service.domain.Trainer;
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
public class TrainerResponse {

    private Long id;

    private String name;

    public static List<TrainerResponse> fromTrainee(List<Trainer> trainers) {
        return trainers.stream()
                .map(TrainerResponse::fromTrainer)
                .collect(Collectors.toList());
    }

    public static TrainerResponse fromTrainer(Trainer trainer) {
        return TrainerResponse.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .build();
    }
}

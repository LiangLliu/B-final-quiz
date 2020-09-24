package com.example.demo.controller.dto;

import com.example.demo.service.domain.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerCreateRequest {

    private String name;

    public static Trainer toTrainer(TrainerCreateRequest request) {
        return Trainer.builder()
                .name(request.getName())
                .build();
    }
}

package com.example.demo.request;


import com.example.demo.domain.Trainer;
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

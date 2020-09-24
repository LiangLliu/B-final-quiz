package com.example.demo.service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Groups {

    private Long id;

    private String name;

    private Integer Ranking;

    private List<Trainer> trainers;

    private List<Trainee> trainees;
}

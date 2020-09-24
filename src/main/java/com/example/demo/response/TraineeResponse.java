package com.example.demo.response;

import com.example.demo.domain.Trainee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TraineeResponse {

    private Long id;

    private String name;

    private String office;

    private String email;

    private String github;

    private String zoomId;

    public static TraineeResponse fromTrainee(Trainee trainee) {

        return TraineeResponse.builder()
                .id(trainee.getId())
                .name(trainee.getName())
                .github(trainee.getGithub())
                .office(trainee.getOffice())
                .zoomId(trainee.getZoomId())
                .email(trainee.getEmail())
                .build();
    }
}

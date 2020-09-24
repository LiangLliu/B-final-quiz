package com.example.demo.request;

import com.example.demo.domain.Trainee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TraineeCreateRequest {
    private String name;

    private String office;

    private String email;

    private String github;

    private String zoomId;

    public static Trainee toTrainee(TraineeCreateRequest request) {
        return Trainee.builder()
                .name(request.getName())
                .github(request.getGithub())
                .office(request.getOffice())
                .zoomId(request.getZoomId())
                .email(request.getEmail())
                .build();
    }
}

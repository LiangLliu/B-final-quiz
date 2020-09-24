package com.example.demo.controller.dto;

import com.example.demo.service.domain.Trainee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TraineeCreateRequest {

    @NotBlank(message = "学员名字不能为空")
    private String name;

    @NotBlank(message = "office不能为空")
    private String office;

    @NotBlank(message = "email不能为空")
    @Email(message = "邮箱不合法")
    private String email;

    @NotBlank(message = "github账号不能为空")
    private String github;

    @NotBlank(message = "zoomId不能为空")
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

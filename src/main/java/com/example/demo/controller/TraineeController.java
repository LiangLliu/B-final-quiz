package com.example.demo.controller;

import com.example.demo.request.TraineeCreateRequest;
import com.example.demo.response.TraineeResponse;
import com.example.demo.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeResponse createTrainee(@RequestBody TraineeCreateRequest request) {
        return traineeService.createTrainee(request);
    }
}

package com.example.demo.controller;

import com.example.demo.request.TrainerCreateRequest;
import com.example.demo.response.TraineeResponse;
import com.example.demo.response.TrainerResponse;
import com.example.demo.service.TrainerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerResponse createTrainer(@RequestBody TrainerCreateRequest request) {
        return trainerService.createTrainer(request);
    }


}

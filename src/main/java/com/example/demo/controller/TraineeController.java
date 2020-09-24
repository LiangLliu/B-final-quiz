package com.example.demo.controller;

import com.example.demo.controller.dto.TraineeCreateRequest;
import com.example.demo.controller.dto.TraineeResponse;
import com.example.demo.service.TraineeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainees")
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }


    @GetMapping
    public List<TraineeResponse> getTrainee(@RequestParam(name = "grouped") boolean grouped) {
        return traineeService.getAllTrainee(grouped);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TraineeResponse createTrainee(@RequestBody TraineeCreateRequest request) {
        return traineeService.createTrainee(request);
    }

    @DeleteMapping("/{traineeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTraineeById(@PathVariable("traineeId") Long traineeId) {
        traineeService.deleteTraineeById(traineeId);
    }
}

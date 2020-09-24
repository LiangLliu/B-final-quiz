package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.request.TraineeCreateRequest;
import com.example.demo.response.TraineeResponse;
import org.springframework.stereotype.Service;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public TraineeResponse createTrainee(TraineeCreateRequest request) {
        Trainee trainee = TraineeCreateRequest.toTrainee(request);
        Trainee saveTrainee = traineeRepository.save(trainee);
        return TraineeResponse.fromTrainee(saveTrainee);
    }

}

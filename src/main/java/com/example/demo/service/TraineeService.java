package com.example.demo.service;


import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.entity.TraineeEntity;
import com.example.demo.controller.dto.TraineeCreateRequest;
import com.example.demo.controller.dto.TraineeResponse;
import com.example.demo.service.domain.Trainee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public TraineeResponse createTrainee(TraineeCreateRequest request) {
        Trainee trainee = TraineeCreateRequest.toTrainee(request);
        TraineeEntity traineeEntity = traineeRepository.save(TraineeEntity.fromTrainee(trainee));

        return TraineeResponse.fromTrainee(TraineeEntity.toTrainee(traineeEntity));
    }

    public List<TraineeResponse> getAllTrainee(boolean grouped) {
        if (!grouped) {
            List<TraineeEntity> traineeEntities = traineeRepository.findAll();
            List<Trainee> trainees = TraineeEntity.toTrainee(traineeEntities);
            return TraineeResponse.fromTrainee(trainees);
        }
        return null;
    }

    public void deleteTraineeById(Long traineeId) {
        // todo: 判断Trainee存在
        traineeRepository.deleteById(traineeId);
    }
}

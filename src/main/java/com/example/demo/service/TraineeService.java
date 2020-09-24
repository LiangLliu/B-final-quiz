package com.example.demo.service;

import com.example.demo.domain.Trainee;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.request.TraineeCreateRequest;
import com.example.demo.response.TraineeResponse;
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
        Trainee saveTrainee = traineeRepository.save(trainee);
        return TraineeResponse.fromTrainee(saveTrainee);
    }

    public List<TraineeResponse> getAllTrainee(boolean grouped) {
        if (!grouped) {
            List<Trainee> trainees = traineeRepository.findAll();
            return TraineeResponse.fromTrainee(trainees);
        }
        return null;
    }

    public void deleteTraineeById(Long traineeId) {
        // todo: 判断Trainee存在

        traineeRepository.deleteById(traineeId);
    }
}

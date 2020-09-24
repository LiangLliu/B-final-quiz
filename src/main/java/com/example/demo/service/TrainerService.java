package com.example.demo.service;

import com.example.demo.domain.Trainer;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.request.TrainerCreateRequest;
import com.example.demo.response.TrainerResponse;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public TrainerResponse createTrainer(TrainerCreateRequest request) {

        Trainer trainer = TrainerCreateRequest.toTrainer(request);

        Trainer saveTrainer = trainerRepository.save(trainer);

        return TrainerResponse.fromTrainer(saveTrainer);

    }

    public List<TrainerResponse> getAllTrainer(boolean grouped) {

        if (!grouped) {
            List<Trainer> trainers = trainerRepository.findAll();
            return TrainerResponse.fromTrainee(trainers);
        }

        return null;
    }
}

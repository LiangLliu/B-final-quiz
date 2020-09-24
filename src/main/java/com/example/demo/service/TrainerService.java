package com.example.demo.service;


import com.example.demo.repository.entity.TrainerEntity;

import com.example.demo.service.domain.Trainer;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.controller.dto.TrainerCreateRequest;
import com.example.demo.controller.dto.TrainerResponse;

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
        TrainerEntity saveEntity = trainerRepository.save(TrainerEntity.fromTrainer(trainer));
        return TrainerResponse.fromTrainer(TrainerEntity.toTrainer(saveEntity));
    }

    public List<TrainerResponse> getAllTrainer(boolean grouped) {

        if (!grouped) {
            List<TrainerEntity> trainerEntities = trainerRepository.findAll();
            List<Trainer> trainers = TrainerEntity.toTrainer(trainerEntities);
            return TrainerResponse.fromTrainer(trainers);
        }
        return null;
    }

    public void deleteTrainerById(Long trainerId) {
        // todo: 判断Trainer存在
        trainerRepository.deleteById(trainerId);
    }
}

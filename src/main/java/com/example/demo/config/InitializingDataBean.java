package com.example.demo.config;

import com.example.demo.repository.entity.TraineeEntity;
import com.example.demo.repository.entity.TrainerEntity;
import com.example.demo.service.domain.Trainee;
import com.example.demo.service.domain.Trainer;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.util.ParseFile;
import com.example.demo.util.PinYinUtil;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitializingDataBean implements ApplicationRunner {

    private static final String FILE_TRAINEE_NAME = "classpath:data/traineeName.txt";
    private static final String FILE_TRAINER_NAME = "classpath:data/trainerName.txt";


    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public InitializingDataBean(TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> traineeNames = ParseFile.getStudentName(FILE_TRAINEE_NAME);
        List<String> trainerNames = ParseFile.getStudentName(FILE_TRAINER_NAME);

        List<TraineeEntity> traineeEntities = traineeNames.stream().map(
                it -> {
                    String pinyinName = PinYinUtil.toPinyin(it);
                    return TraineeEntity.builder()
                            .name(it)
                            .zoomId(pinyinName)
                            .office("北京")
                            .email(pinyinName + "@gtb.com")
                            .github(pinyinName)
                            .build();
                }
        ).collect(Collectors.toList());

        List<TraineeEntity> traineeEntities1 = traineeRepository.saveAll(traineeEntities);
        traineeEntities1.forEach(System.out::println);

        List<TrainerEntity> trainerEntities = trainerNames.stream()
                .map(it -> TrainerEntity.builder().name(it).build())
                .collect(Collectors.toList());

        List<TrainerEntity> trainerEntities1 = trainerRepository.saveAll(trainerEntities);
        trainerEntities1.forEach(System.out::println);

    }

}

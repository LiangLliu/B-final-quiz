package com.example.demo.config;

import com.example.demo.domain.Trainee;
import com.example.demo.domain.Trainer;
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

        List<Trainee> trainees = traineeNames.stream().map(
                it -> {
                    String pinyinName = PinYinUtil.toPinyin(it);
                    return Trainee.builder()
                            .name(it)
                            .zoomId(pinyinName)
                            .office("北京")
                            .email(pinyinName + "@gtb.com")
                            .github(pinyinName)
                            .build();
                }
        ).collect(Collectors.toList());

        List<Trainee> trainees1 = traineeRepository.saveAll(trainees);
        trainees1.forEach(it -> System.out.println(it));

        List<Trainer> trainers = trainerNames.stream()
                .map(it -> Trainer.builder().name(it).build())
                .collect(Collectors.toList());

        List<Trainer> trainers1 = trainerRepository.saveAll(trainers);
        trainers1.forEach(it -> System.out.println(it));

    }

}

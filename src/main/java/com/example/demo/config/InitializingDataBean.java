package com.example.demo.config;

import com.example.demo.domain.Trainee;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.util.ParseFile;
import com.example.demo.util.PinYinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitializingDataBean implements ApplicationRunner {

    private static final String FILE_NAME = "classpath:data/studentName.txt";

    private final TraineeRepository traineeRepository;

    public InitializingDataBean(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> studentNames = ParseFile.getStudentName(FILE_NAME);

        List<Trainee> trainees = studentNames.stream().map(
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
    }

}

package com.example.demo.service;

import com.example.demo.controller.dto.GroupModifyRequest;
import com.example.demo.controller.dto.GroupsResponse;
import com.example.demo.exception.BaseBadRequestException;
import com.example.demo.exception.GroupIdNotFoundException;
import com.example.demo.repository.GroupsRepository;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.repository.entity.GroupsEntity;
import com.example.demo.repository.entity.TraineeEntity;
import com.example.demo.repository.entity.TrainerEntity;
import com.example.demo.service.domain.Groups;
import com.example.demo.service.domain.Trainee;
import com.example.demo.service.domain.Trainer;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupsService {

    private final GroupsRepository groupsRepository;

    private final TraineeRepository traineeRepository;

    private final TrainerRepository trainerRepository;

    public GroupsService(GroupsRepository groupsRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.groupsRepository = groupsRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }

    public List<GroupsResponse> autoGrouping() {

        List<TraineeEntity> traineeEntities = traineeRepository.findAll();
        List<TrainerEntity> trainerEntities = trainerRepository.findAll();
        if (traineeEntities.size() < 2) {
            throw new BaseBadRequestException("教师小于2人，分组失败");
        }

        List<Trainee> trainees = TraineeEntity.toTrainee(traineeEntities);
        List<Trainer> trainers = TrainerEntity.toTrainer(trainerEntities);

        List<Groups> groups = grouping(trainees, trainers);

        groups.forEach(System.out::println);

        List<GroupsEntity> groupsEntities = GroupsEntity.fromGroups(groups);

        groupsRepository.deleteAll();
        List<Groups> groupsList = GroupsEntity.toGroups(groupsRepository.saveAll(groupsEntities));

        return GroupsResponse.fromGroups(groupsList);
    }

    private List<Groups> grouping(List<Trainee> trainees, List<Trainer> trainers) {
        Collections.shuffle(trainees);
        Collections.shuffle(trainers);

        int number = trainers.size() / 2;

        Map<Integer, Groups> groupsHashMap = new HashMap<>();
        for (int i = 0; i < number; i++) {
            groupsHashMap.put(i + 1, new Groups(new ArrayList<>(), new ArrayList<>()));

            groupsHashMap.get(i + 1).setRanking((long) (i + 1));
            groupsHashMap.get(i + 1).setName((i + 1) + " 组");
            groupsHashMap.get(i + 1)
                    .setTrainers(Arrays.asList(trainers.get(i * 2), trainers.get(i * 2 + 1)));
        }

        for (int i = 0; i < trainees.size(); i++) {
            int index = (i % number) + 1;
            groupsHashMap.get(index).getTrainees().add(trainees.get(i));
        }

        return new ArrayList<>(groupsHashMap.values());
    }

    public List<GroupsResponse> getAllGroup() {
        List<GroupsEntity> groupsEntityList = groupsRepository.findAll();
        List<Groups> groupsList = GroupsEntity.toGroups(groupsEntityList);
        return GroupsResponse.fromGroups(groupsList);
    }

    public void modifyGroupName(long groupId, GroupModifyRequest request) {

        Optional<GroupsEntity> optionalGroupsEntity = groupsRepository.findById(groupId);

        if (!optionalGroupsEntity.isPresent()) {
            throw new GroupIdNotFoundException("此分组不存在");
        }

        GroupsEntity entity = optionalGroupsEntity.get();

        List<GroupsEntity> groupsEntityList = groupsRepository.findAll();

        if (groupsEntityList.stream().anyMatch(it -> request.getName().equals(it.getName()))) {
            throw new BaseBadRequestException("分组名字重复，修改失败");
        }

        entity.setName(request.getName());
        groupsRepository.save(entity);
    }
}

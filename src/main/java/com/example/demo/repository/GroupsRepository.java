package com.example.demo.repository;

import com.example.demo.repository.entity.GroupsEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<GroupsEntity, Long> {
}

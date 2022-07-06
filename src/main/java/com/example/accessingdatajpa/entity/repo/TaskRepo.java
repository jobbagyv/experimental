package com.example.accessingdatajpa.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatajpa.entity.Task;

public interface TaskRepo extends CrudRepository<Task, Long> {

}

package com.example.accessingdatajpa.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQueryByExampleExecutor;

public interface CombinedRepo<T> extends CrudRepository<T, Long>, EntityGraphQueryByExampleExecutor<T> {

}

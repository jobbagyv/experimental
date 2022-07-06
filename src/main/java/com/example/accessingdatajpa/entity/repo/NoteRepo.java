package com.example.accessingdatajpa.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.cosium.spring.data.jpa.entity.graph.repository.EntityGraphQueryByExampleExecutor;
import com.example.accessingdatajpa.entity.Customer;
import com.example.accessingdatajpa.entity.Note;

public interface NoteRepo extends CrudRepository<Note, Long>, EntityGraphQueryByExampleExecutor<Customer> {

}

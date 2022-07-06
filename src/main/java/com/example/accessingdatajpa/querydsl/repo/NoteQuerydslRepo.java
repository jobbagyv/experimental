package com.example.accessingdatajpa.querydsl.repo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatajpa.entity.Note;
import com.querydsl.core.types.Predicate;

public interface NoteQuerydslRepo extends CrudRepository<Note, Long>, QuerydslPredicateExecutor<Note> {
	@EntityGraph("customer-eager")
	@Override
	public Iterable<Note> findAll(Predicate predicate);
}

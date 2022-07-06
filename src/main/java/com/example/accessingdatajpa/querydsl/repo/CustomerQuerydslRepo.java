package com.example.accessingdatajpa.querydsl.repo;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatajpa.entity.Customer;

public interface CustomerQuerydslRepo extends CrudRepository<Customer, Long>, QuerydslPredicateExecutor<Customer> {

}

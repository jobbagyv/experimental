package com.example.accessingdatajpa.entity.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.accessingdatajpa.entity.Customer;

@Repository
public interface CustomerRepository extends  CrudRepository<Customer, Long> {

}

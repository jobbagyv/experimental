package com.example.accessingdatajpa.specification.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.accessingdatajpa.entity.Customer;

@Repository
public interface CustomerSpecificationRepo extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

}

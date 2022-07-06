package com.example.accessingdatajpa.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatajpa.entity.Image;

public interface ImageRepo extends CrudRepository<Image, Long> {

}

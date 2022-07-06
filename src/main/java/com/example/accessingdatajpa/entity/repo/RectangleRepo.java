package com.example.accessingdatajpa.entity.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatajpa.entity.Image;
import com.example.accessingdatajpa.entity.Rectangle;

public interface RectangleRepo extends CrudRepository<Rectangle, Long> {

}

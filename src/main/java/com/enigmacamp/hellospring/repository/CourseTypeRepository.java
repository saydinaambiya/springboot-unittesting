package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseTypeRepository extends JpaRepository<CourseType, String> {
}

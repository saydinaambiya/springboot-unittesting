package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, String> {
    //findBy Column
    List<Course> findByTitleContains(String title);

    List<Course> findByDescriptionContains(String description);
}

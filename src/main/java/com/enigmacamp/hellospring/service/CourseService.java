package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Course> list();

    Course create(Course course);

    Optional<Course> get(String id);

    void update(Course course, String id);

    void delete(String id);

    void createBulk(List<Course> courses);
}

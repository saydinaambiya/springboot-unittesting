package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> getAll() throws Exception;

    Course create(Course course) throws Exception;

    Optional<Course> findById(String id) throws Exception;

    Optional<List<Course>> findBy(String by, String value) throws Exception;

    void update(Course course, String id) throws Exception;

    void delete(String id) throws Exception;

    void bulk(List<Course> courses) throws Exception;
}

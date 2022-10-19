package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> list() throws Exception;

    Course create(Course course);

    Course get(String id) throws Exception;

    List<Course> getBy(String key, String value) throws Exception;

    void update(Course course, String id);

    void delete(String id);

    void createBulk(List<Course> courses);
}

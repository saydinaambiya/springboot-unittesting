package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.model.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    Page<Course> list(Integer page, Integer size, String direction, String sortBy) throws Exception;

    List<Course> list() throws Exception;

    Course create(Course course) throws Exception;

    Course get(String id) throws Exception;

    List<Course> getBy(String key, String value) throws Exception;

    void update(Course course, String id) throws Exception;

    void delete(String id) throws Exception;

    void createBulk(List<Course> courses);
}

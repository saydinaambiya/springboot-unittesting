package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> list() {
        try {
            return courseRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Course create(Course course) {
        try {
            return courseRepository.create(course);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Course> get(String id) {
        return Optional.empty();
    }

    @Override
    public void update(Course course, String id) {
        try {
            courseRepository.update(course, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try {
            courseRepository.delete(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createBulk(List<Course> courses) {
        try {
            courseRepository.bulk(courses);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

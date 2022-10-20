package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.exception.NotFoundException;
import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.repository.CourseArrayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Profile("console")
public class CourseServiceArrayImpl implements CourseService {

    @Value("${course.data.length}")
    Integer dataLength;
    @Autowired
    private CourseArrayRepository courseRepository;

    @Override
    public List<Course> list() throws Exception {
        List<Course> result = courseRepository.getAll();
        if (result.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return result;
    }

    @Override
    public Course create(Course course) throws Exception {
        if (!(courseRepository.getAll().size() < dataLength)) {
            throw new Exception("Data is full");
        }
        return courseRepository.create(course);
    }

    @Override
    public Course get(String id) throws Exception {
        Optional<Course> result = courseRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return result.get();
    }

    @Override
    public List<Course> getBy(String key, String value) throws Exception {
        Optional<List<Course>> result = courseRepository.findBy(key, value);
        if (result.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return result.get();
    }

    @Override
    public void update(Course course, String id) throws Exception {
        try {
            Course existingCourse = get(id);
            courseRepository.update(course, existingCourse.getCourseId());
        } catch (NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            Course course = get(id);
            courseRepository.delete(course.getCourseId());
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
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

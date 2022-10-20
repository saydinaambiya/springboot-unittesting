package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.exception.EntityExistException;
import com.enigmacamp.hellospring.exception.NotFoundException;
import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("api")
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;

    @Autowired
    ModelMapper modelMapper;

    public CourseServiceImpl(@Autowired CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> list() {
        List<Course> courses = new ArrayList<>();
        Iterable<Course> result = courseRepository.findAll();
        for (Course course : result) {
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Course create(Course course) {
        try {
            Course newCourse = courseRepository.save(course);
            return newCourse;
        } catch (DataIntegrityViolationException e) {
            throw new EntityExistException();
        }
    }

    @Override
    public Course get(String id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new NotFoundException("Course not found");
        }
        return course.get();
    }

    @Override
    public List<Course> getBy(String key, String value) {
        switch (key) {
            case "title":
                return courseRepository.findByTitleContains(value);
            case "description":
                return courseRepository.findByDescriptionContains(value);
            default:
                return courseRepository.findAll();
        }
    }

    @Override
    public void update(Course course, String id) {
        try {
            Course existingCourse = get(id);
            modelMapper.map(course, existingCourse);
            courseRepository.save(existingCourse);
        } catch (NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            Course existingCourse = get(id);
            courseRepository.delete(existingCourse);
        } catch (NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }

    @Override
    public void createBulk(List<Course> courses) {

    }
}

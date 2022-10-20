package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.util.GetMethodReflection;
import com.enigmacamp.hellospring.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("console")
public class CourseRepositoryArrayImpl implements CourseArrayRepository {
    @Autowired
    RandomStringGenerator randomStringGenerator;
    private final List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public Course create(Course course) {
        course.setCourseId(randomStringGenerator.random());
        courses.add(course);
        return course;
    }

    @Override
    public Optional<Course> findById(String id) {
        for (Course course : courses) {
            if (course.getCourseId().equals(id)) {
                return Optional.of(course);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Course>> findBy(String by, String value) throws Exception {
        List<Course> courseList = new ArrayList<>();
        GetMethodReflection reflection = new GetMethodReflection<Course>();
        for (Course course : courses) {
            String title = (String) reflection.getMethodInvoke(course, by);
            if (title.contains(value)) {
                courseList.add(course);
            }
        }
        if (courseList.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(courseList);
    }

    @Override
    public void update(Course course, String id) {
        for (Course existingCourse : courses) {
            if (existingCourse.getCourseId().equals(id)) {
                existingCourse.setLink(course.getLink());
                existingCourse.setTitle(course.getTitle());
                existingCourse.setDescription(course.getDescription());
                break;
            }
        }
    }

    @Override
    public void delete(String id) {
        for (Course course : courses) {
            if (course.getCourseId().equals(id)) {
                courses.remove(course);
                break;
            }
        }
    }

    @Override
    public void bulk(List<Course> courses) throws Exception {

    }
}

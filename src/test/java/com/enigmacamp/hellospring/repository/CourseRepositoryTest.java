package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.repository.spec.CourseSpecification;
import com.enigmacamp.hellospring.repository.spec.SearchCriteria;
import com.enigmacamp.hellospring.util.QueryOperator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Sql(value = {"classpath:course.sql"})
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void itShould_ReturnListCourse_When_FindAll() {
        Specification dummySpec = new CourseSpecification(new SearchCriteria("title", QueryOperator.LIKE, "title"));
        List actualCourseList = courseRepository.findAll(dummySpec);
        assertEquals(2, actualCourseList.size());
    }

    @Test
    void itShould_ReturnPageCourse_When_FindWithInfoAndType(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.valueOf("ASC"), "courseId"));
        Page<Course> courseList = courseRepository.findWithInfoAndType(pageable);
        assertEquals(2, courseList.getContent().size());
    }
}

package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.repository.spec.CourseSpecification;
import com.enigmacamp.hellospring.repository.spec.SearchCriteria;
import com.enigmacamp.hellospring.util.QueryOperator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(scripts={"classpath:course.sql"})
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void itShouldReturnListCourseWhenFindAll() {
        Specification spec = new CourseSpecification(new SearchCriteria("title", QueryOperator.LIKE, "title"));
        List<Course> courseList = courseRepository.findAll(spec);
        assertEquals(2, courseList.size());
    }
    @Test
    void itShouldReturnPageCourseWhenFindWithInfoAndType() {
        Sort sort = Sort.by(Sort.Direction.valueOf("ASC"), "courseId");
        Pageable pageable = PageRequest.of((1 - 1), 10, sort);
        Page<Course> courseList = courseRepository.findWithInfoAndType(pageable);
        assertEquals(courseList.getContent().size(), 2);
    }
}

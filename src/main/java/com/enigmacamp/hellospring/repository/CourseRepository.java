package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CourseRepository extends JpaRepository<Course, String>, JpaSpecificationExecutor {

    @EntityGraph(value = "Course_With_CourseType_And_Info", type = EntityGraph.EntityGraphType.LOAD)
    List<Course> findAll(Specification specification);

    @Query(value = "from Course c join fetch c.courseInfo join fetch c.courseType", countQuery = "select count(i) from Course i")
    Page<Course> findWithInfoAndType(Pageable pageable);
}

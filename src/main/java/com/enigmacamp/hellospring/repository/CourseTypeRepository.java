package com.enigmacamp.hellospring.repository;

import com.enigmacamp.hellospring.model.CourseType;
import com.enigmacamp.hellospring.repository.projection.CourseTypeView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CourseTypeRepository extends JpaRepository<CourseType, String> {

    @Query(value = "SELECT u FROM CourseType u")
    Page<CourseTypeView> findOnlyCourse(Pageable pageable);
}

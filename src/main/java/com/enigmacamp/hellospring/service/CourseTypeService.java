package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.model.CourseType;
import com.enigmacamp.hellospring.repository.projection.CourseTypeView;
import org.springframework.data.domain.Page;

public interface CourseTypeService {
    Page<CourseTypeView> list(Integer page, Integer size, String direction, String sortBy) throws Exception;

    Page<CourseType> listWithCourses(Integer page, Integer size, String direction, String sortBy) throws Exception;

    CourseType create(CourseType courseType) throws Exception;

}

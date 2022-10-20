package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.model.CourseType;
import org.springframework.data.domain.Page;

public interface CourseTypeService {
    Page<CourseType> list(Integer page, Integer size, String direction, String sortBy) throws Exception;

    CourseType create(CourseType courseType) throws Exception;

}

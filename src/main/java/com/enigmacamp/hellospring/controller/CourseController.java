package com.enigmacamp.hellospring.controller;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.model.request.NewCourseRequest;
import com.enigmacamp.hellospring.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity getAllCourse() {
        List<Course> courseList = courseService.list();
        return ResponseEntity.status(HttpStatus.OK).body(courseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourse(@PathVariable String id) {
        try {
            Course result = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getCourseBy(@RequestParam String keyword,
                                      @RequestParam String value) {
        try {
            List<Course> result = courseService.getBy(keyword, value);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody NewCourseRequest request) {
        Course newCourse = modelMapper.map(request, Course.class);
        Course result = courseService.create(newCourse);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(result);
    }

}

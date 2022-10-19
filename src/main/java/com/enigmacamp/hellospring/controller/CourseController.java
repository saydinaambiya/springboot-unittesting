package com.enigmacamp.hellospring.controller;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.model.request.NewCourseRequest;
import com.enigmacamp.hellospring.model.response.ErrorResponse;
import com.enigmacamp.hellospring.model.response.SuccessResponse;
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
        try {
            List<Course> courseList = courseService.list();
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                    courseList, "Success Get All Course"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCourse(@PathVariable String id) {
        try {
            Course result = courseService.get(id);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(result, "Success get course with id"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @GetMapping(params = {"keyword", "value"})
    public ResponseEntity getCourseBy(@RequestParam String keyword,
                                      @RequestParam String value) {
        try {
            List<Course> result = courseService.getBy(keyword, value);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(
                    result, "Success get course by " + keyword
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity createCourse(@RequestBody NewCourseRequest request) {
        try {
            Course newCourse = modelMapper.map(request, Course.class);
            Course result = courseService.create(newCourse);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new SuccessResponse<>(
                            result, "Success create course"
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse("X02", e.getMessage()));
        }
    }

}

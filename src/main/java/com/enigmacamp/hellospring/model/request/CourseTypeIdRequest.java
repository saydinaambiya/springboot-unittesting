package com.enigmacamp.hellospring.model.request;

import javax.validation.constraints.NotBlank;

public class CourseTypeIdRequest {
    @NotBlank(message = "{invalid.uuid}")
    private String courseTypeId;

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }
}

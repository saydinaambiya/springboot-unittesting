package com.enigmacamp.hellospring.model.request;

import javax.validation.constraints.NotBlank;

public class NewCourseRequest {
    @NotBlank(message = "{invalid.title.required}")
    private String title;

    private String description;
    @NotBlank(message = "{invalid.link.required}")
    private String link;

    private NewCourseInfoRequest courseInfo;

    private NewCourseTypeRequest courseType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public NewCourseInfoRequest getCourseInfo() {
        return courseInfo;
    }

    public void setCourseInfo(NewCourseInfoRequest courseInfo) {
        this.courseInfo = courseInfo;
    }

    public NewCourseTypeRequest getCourseType() {
        return courseType;
    }

    public void setCourseType(NewCourseTypeRequest courseType) {
        this.courseType = courseType;
    }
}

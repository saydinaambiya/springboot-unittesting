package com.enigmacamp.hellospring.model.request;

import javax.persistence.Column;

public class NewCourseInfoRequest {
    @Column(name = "duration", nullable = false, length = 50)
    private String duration;

    @Column(name = "level", nullable = false, length = 10)
    private String level;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

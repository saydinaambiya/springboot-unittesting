package com.enigmacamp.hellospring.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "mst_course_type")
public class CourseType {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "course_type_id")
    private String courseTypeId;

    @Column(name = "type_name", nullable = false, length = 150, unique = true)
    private String typeName;

//    @OneToMany(mappedBy = "courseType", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties({"courseType", "courseInfo"})
//    private List<Course> courseList;

    public String getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(String courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

//    public List<Course> getCourseList() {
//        return courseList;
//    }
//
//    public void setCourseList(List<Course> courseList) {
//        this.courseList = courseList;
//    }

//    @Override
//    public String toString() {
//        return "CourseType{" +
//                "courseTypeId='" + courseTypeId + '\'' +
//                ", typeName='" + typeName + '\'' +
//                ", courseList=" + courseList +
//                '}';
//    }

    @Override
    public String toString() {
        return "CourseType{" +
                "courseTypeId='" + courseTypeId + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}

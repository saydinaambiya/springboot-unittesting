package com.enigmacamp.hellospring;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("console")
public class Console implements CommandLineRunner {
    @Autowired
    CourseService courseService;

    @Override
    public void run(String... args) {
        System.out.println("Hello Spring Boot");
        Course course1 = new Course();
        course1.setDescription("Spring Boot");
        course1.setLink("https://www.tutorialspoint.com/spring_boot/spring_boot_introduction.htm");
        course1.setTitle("Spring Framework");

        courseService.create(course1);
        printResult();
    }

    private void printResult() {
        List<Course> courseList = courseService.list();
        for (Course course : courseList) {
            System.out.println(course.toString());
        }
    }
}

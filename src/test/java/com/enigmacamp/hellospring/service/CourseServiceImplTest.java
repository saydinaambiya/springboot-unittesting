package com.enigmacamp.hellospring.service;

import com.enigmacamp.hellospring.exception.EntityExistException;
import com.enigmacamp.hellospring.exception.NotFoundException;
import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.model.CourseType;
import com.enigmacamp.hellospring.repository.CourseRepository;
import com.enigmacamp.hellospring.repository.CourseTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceImplTest {
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseTypeRepository courseTypeRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void itShouldGetCourseById() {
        Course course = new Course();
        course.setTitle("Dummy title");
        course.setDescription("Dummy description");
        course.setLink("Dummy link");
        when(courseRepository.findById(anyString())).thenReturn(Optional.of(course));
        try {
            Course actualCourse = courseService.get(anyString());
            assertEquals(course.getTitle(), actualCourse.getTitle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void itShouldThrowNotFoundWhenFindByIdIsEmpty() {
        when(courseRepository.findById(anyString())).thenReturn(Optional.empty());
        try {
            assertThrows(NotFoundException.class, new Executable() {
                @Override
                public void execute() throws Throwable {
                    courseService.get(anyString());
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void itShouldGetAllCourseList() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course());
        courseList.add(new Course());
        Page<Course> courses = new PageImpl(courseList);
        when(courseRepository.findWithInfoAndType(isA(Pageable.class))).thenReturn(courses);
        try {
            Page<Course> actualCourse = courseService.list(1, 10, "ASC", "courseId");
            assertEquals(courses.getContent().size(), courseList.size());
            assertEquals(courses.getTotalPages(), 1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void itShouldSaveCourse() {
        Course savedCourseMock = mock(Course.class);
        Course course = new Course();
        course.setTitle("Dummy title");
        course.setDescription("Dummy description");
        course.setLink("Dummy link");
        CourseType courseType = new CourseType();
        courseType.setCourseTypeId("Dummy Id");
        courseType.setTypeName("Dummy Course Type");
        course.setCourseType(courseType);
        when(courseTypeRepository.findById(courseType.getCourseTypeId())).thenReturn(Optional.of(courseType));
        when(courseRepository.save(course)).thenReturn(savedCourseMock);

        try {
            Course actualCourse = courseService.create(course);
            assertEquals(savedCourseMock.getCourseId(), actualCourse.getCourseId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void itShouldThrowEntityExistWhenCourseIsExist() {
        Course newCourse = new Course();
        newCourse.setTitle("Dummy title");
        newCourse.setDescription("Dummy description");
        newCourse.setLink("Dummy link");
        CourseType courseType = new CourseType();
        courseType.setCourseTypeId("Dummy Id");
        courseType.setTypeName("Dummy Course Type");
        newCourse.setCourseType(courseType);

        when(courseTypeRepository.findById(courseType.getCourseTypeId())).thenReturn(Optional.of(new CourseType()));
        when(courseRepository.save(newCourse)).thenThrow(new DataIntegrityViolationException("Data is Exist"));

        assertThrows(EntityExistException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                courseService.create(newCourse);
            }
        });
    }

    @Test
    void itShouldGetAllCourseWhenSearchDynamically() {
        List<Course> result = new ArrayList<>();
        result.add(new Course());
        result.add(new Course());
        when(courseRepository.findAll(isA(Specification.class))).thenReturn(result);
        List<Course> actualResult = courseService.getBy("title", "dummy");
        assertEquals(result.size(), actualResult.size());
    }

    @Test
    void itShouldThrowNotFoundWhenSearchDynamicallyIsEmpty() {
        when(courseRepository.findAll(isA(Specification.class))).thenReturn(new ArrayList<>());
        assertThrows(NotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                courseService.getBy("title", "dummy");
            }
        });
    }
}

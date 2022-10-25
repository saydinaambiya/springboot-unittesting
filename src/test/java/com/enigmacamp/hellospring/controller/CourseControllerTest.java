package com.enigmacamp.hellospring.controller;

import com.enigmacamp.hellospring.model.Course;
import com.enigmacamp.hellospring.model.request.NewCourseRequest;
import com.enigmacamp.hellospring.model.response.PagingResponse;
import com.enigmacamp.hellospring.model.response.SuccessResponse;
import com.enigmacamp.hellospring.service.CourseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
@ActiveProfiles("test")
public class CourseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class CourseConfiguration {
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper();
        }

        @Bean
        public ModelMapper modelMapper() {
            return new ModelMapper();
        }
    }

    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
    private CourseService courseService;

    @Test
    void itShouldReturnResponseOkWhenGetCourseById() throws Exception {
        Course course = new Course();

        SuccessResponse<Course> response = new SuccessResponse<>(course, "Success get course with id");

        String uuidDummy = UUID.randomUUID().toString();
        when(courseService.get(uuidDummy)).thenReturn(course);
//        MvcResult mvcResult = mockMvc.perform(get("/courses/{id}", uuidDummy))
//                .andExpect(status().isOk())
//                .andReturn();
//        String actualResponseBody = mvcResult.getResponse().getContentAsString();
//        assertEquals(actualResponseBody, objectMapper.writeValueAsString(response));

        mockMvc.perform(get("/courses/{id}", uuidDummy))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void itShouldReturnBadParameterWhenGetCourseByIdIsNotUUID() throws Exception {
        mockMvc.perform(get("/courses/{id}", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void itShouldCreateCourseSuccessfully() throws Exception {
        Course course = new Course();
        when(courseService.create(any())).thenReturn(course);
        SuccessResponse<Course> response = new SuccessResponse<>(course, "Success create course");
        NewCourseRequest request = new NewCourseRequest();
        request.setTitle("Dummy title");
        request.setLink("Dummy link");
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

    }

    @Test
    void itShouldThrowBedRequestWhenCreateCourseBodyIsInvalid() throws Exception {
        NewCourseRequest request = new NewCourseRequest();
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void itShouldGetAllCourseWithPaging() throws Exception {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course());
        courseList.add(new Course());
        Page<Course> courses = new PageImpl(courseList);

        Integer page = 1;
        Integer size = 10;
        String sortDirection = "ASC";
        String sortBy = "courseId";
        when(courseService.list(page, size, sortDirection, sortBy)).thenReturn(courses);
        PagingResponse<Course> response = new PagingResponse<>(courses, "Success get course list");

        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("page", String.valueOf(page));
        paramsMap.add("size", String.valueOf(size));
        paramsMap.add("direction", sortDirection);
        paramsMap.add("sortBy", sortBy);

        mockMvc.perform(get("/courses")
                        .params(paramsMap)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    void itShouldGetCourseWjenSearchDynamically() throws Exception {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course());
        courseList.add(new Course());
        String keyword = "title";
        String value = "dummy";
        when(courseService.getBy(keyword, value)).thenReturn(courseList);
        SuccessResponse<List<Course>> response = new SuccessResponse<>(courseList, "Success get course by " + keyword);

        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("keyword", keyword);
        paramsMap.add("value", value);

        mockMvc.perform(get("/courses")
                        .params(paramsMap)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
}

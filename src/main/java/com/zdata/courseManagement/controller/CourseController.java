package com.zdata.courseManagement.controller;


import com.zdata.courseManagement.dto.CourseDTO;
import com.zdata.courseManagement.model.Course;
import com.zdata.courseManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final StudentService service;

    public CourseController(StudentService service) {

        this.service = service;
    }

    // Create a new course
    @PostMapping
    public Course addCourse(@Valid @RequestBody CourseDTO dto){

        return service.addCourse(dto);
    }

    // Get all courses with pagination
    @GetMapping
    public List<Course> getAllCourse(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){

        List<Course> allCourses = service.getAllCourse();
        int fromIndex = Math.min(page * size, allCourses.size());
        int toIndex = Math.min(fromIndex + size, allCourses.size());
        return allCourses.subList(fromIndex, toIndex);
    }
}

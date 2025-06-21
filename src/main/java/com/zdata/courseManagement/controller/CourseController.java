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

    @PostMapping
    public Course addCourse(@Valid @RequestBody CourseDTO dto){
        return service.addCourse(dto);
    }

    @GetMapping
    public List<Course> getAllCourse(){
        return service.getAllCourse();
    }
}

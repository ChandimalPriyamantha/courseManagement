package com.zdata.courseManagement.controller;


import com.zdata.courseManagement.dto.RegistrationResponseDTO;
import com.zdata.courseManagement.dto.StudentDTO;
import com.zdata.courseManagement.model.Student;
import com.zdata.courseManagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;


    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public Student createStudent(@Valid @RequestBody StudentDTO dto){
        return service.addStudent(dto);
    }

    @PostMapping("/{studentId}/register/{courseId}")
    public void registerCourse(@PathVariable UUID studentId, @PathVariable UUID courseId) {
        service.register(studentId, courseId);
    }

    @DeleteMapping("/{studentId}/drop/{courseId}")
    public void dropCourse(@PathVariable UUID studentId, @PathVariable UUID courseId) {
        service.drop(studentId, courseId);
    }

    @GetMapping("/{studentId}/courses")
    public List<RegistrationResponseDTO> getCourses(@PathVariable UUID studentId) {
        return service.getStudentCourse(studentId);
    }
}

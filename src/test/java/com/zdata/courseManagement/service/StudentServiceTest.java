package com.zdata.courseManagement.service;

import com.zdata.courseManagement.dto.CourseDTO;
import com.zdata.courseManagement.dto.RegistrationResponseDTO;
import com.zdata.courseManagement.dto.StudentDTO;
import com.zdata.courseManagement.exception.CustomException;
import com.zdata.courseManagement.model.Course;
import com.zdata.courseManagement.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService();
    }

    @Test
    void addStudent_successfullyAddsStudent() {
        StudentDTO dto = new StudentDTO();
        dto.setName("Alice");
        dto.setEmail("alice@test.com");

        Student saved = studentService.addStudent(dto);

        assertNotNull(saved.getId());
        assertEquals("Alice", saved.getName());
    }

    @Test
    void addStudent_duplicateEmail_throwsException() {
        StudentDTO dto = new StudentDTO();
        dto.setName("Bob");
        dto.setEmail("bob@test.com");

        studentService.addStudent(dto);
        assertThrows(CustomException.class, () -> studentService.addStudent(dto));
    }

    @Test
    void addCourse_successfullyAddsCourse() {
        CourseDTO course = new CourseDTO();
        course.setCode("CS101");
        course.setTitle("Intro to CS");
        course.setInstructor("Dr. Jane");

        Course saved = studentService.addCourse(course);

        assertNotNull(saved.getId());
        assertEquals("CS101", saved.getCode());
    }

    @Test
    void addCourse_duplicateCode_throwsException() {
        CourseDTO dto = new CourseDTO();
        dto.setCode("CS202");
        dto.setTitle("Data Structures");
        dto.setInstructor("Dr. Mark");

        studentService.addCourse(dto);
        assertThrows(CustomException.class, () -> studentService.addCourse(dto));
    }

    @Test
    void registerStudentToCourse_success() {
        UUID studentId = studentService.addStudent(new StudentDTO("Student", "test@student.com")).getId();
        UUID courseId = studentService.addCourse(new CourseDTO("CS999", "Course", "Prof")).getId();

        studentService.register(studentId, courseId);

        List<RegistrationResponseDTO> registeredCourses = studentService.getStudentCourse(studentId);
        assertEquals(1, registeredCourses.size());
        assertEquals("CS999", registeredCourses.get(0).getCourseCode());
    }

    @Test
    void registerTwice_throwsException() {
        UUID studentId = studentService.addStudent(new StudentDTO("Stu", "a@a.com")).getId();
        UUID courseId = studentService.addCourse(new CourseDTO("X100", "X Course", "Instructor")).getId();

        studentService.register(studentId, courseId);
        assertThrows(CustomException.class, () -> studentService.register(studentId, courseId));
    }

    @Test
    void dropUnregisteredCourse_throwsException() {
        UUID studentId = studentService.addStudent(new StudentDTO("Jack", "jack@abc.com")).getId();
        UUID courseId = studentService.addCourse(new CourseDTO("Z999", "Z Course", "Zara")).getId();

        assertThrows(CustomException.class, () -> studentService.drop(studentId, courseId));
    }

    @Test
    void dropRegisteredCourse_success() {
        UUID studentId = studentService.addStudent(new StudentDTO("Luke", "luke@abc.com")).getId();
        UUID courseId = studentService.addCourse(new CourseDTO("Y123", "Y Course", "Yin")).getId();

        studentService.register(studentId, courseId);
        studentService.drop(studentId, courseId);

        List<RegistrationResponseDTO> list = studentService.getStudentCourse(studentId);
        assertTrue(list.isEmpty());
    }

    @Test
    void getStudentCourses_invalidStudent_throwsException() {
        UUID fakeId = UUID.randomUUID();
        assertThrows(CustomException.class, () -> studentService.getStudentCourse(fakeId));
    }

}

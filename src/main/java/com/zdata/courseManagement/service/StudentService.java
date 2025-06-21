package com.zdata.courseManagement.service;

import com.zdata.courseManagement.dto.CourseDTO;
import com.zdata.courseManagement.dto.RegistrationResponseDTO;
import com.zdata.courseManagement.dto.StudentDTO;
import com.zdata.courseManagement.exception.CustomException;
import com.zdata.courseManagement.model.Course;
import com.zdata.courseManagement.model.Registration;
import com.zdata.courseManagement.model.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class StudentService {

    private final Map<UUID, Student> students = new HashMap<>();
    private final Map<UUID, Course> courses = new HashMap<>();
    private final List<Registration> registrations = new ArrayList<>();

    public Student addStudent(StudentDTO dto) {
        if (students.values().stream().anyMatch(s -> s.getEmail().equals(dto.getEmail()))) {
              throw new CustomException("Email already exists.");
        }
        Student student = new Student(UUID.randomUUID(), dto.getName(), dto.getEmail());
        students.put(student.getId(), student);
        return student;
    }

    public Course addCourse(CourseDTO dto){
        if(courses.values().stream().anyMatch(c -> c.getCode().equals(dto.getCode()))){
            throw new CustomException("Course code already exists.");
        }
        Course course = new Course(UUID.randomUUID(), dto.getCode(), dto.getTitle(),dto.getInstructor());
        courses.put(course.getId(), course);
        return course;
    }

    public List<Course> getAllCourse(){
        return new ArrayList<>(courses.values());
    }

    public void register(UUID studentId, UUID courseId){
     validateStudentAndCourse(studentId, courseId);
     boolean alreadyRegistered = registrations.stream().anyMatch(
             r -> r.getStudentId().equals(studentId) && r.getCourseId().equals(courseId));
     if(alreadyRegistered) throw new CustomException("Student already registered for this course.");

     registrations.add(new Registration(studentId, courseId, LocalDateTime.now()));
    }

    public void drop(UUID studentId, UUID courseId){
        validateStudentAndCourse(studentId, courseId);
        boolean removed = registrations.removeIf(r -> r.getStudentId().equals(studentId) && r.getCourseId().equals(courseId));
        if(!removed) throw new CustomException("Course not registered..");


    }

    public List<RegistrationResponseDTO> getStudentCourse(UUID studentId){
        if(!students.containsKey(studentId)) {
            throw new CustomException("Student not found.");
        }

        List<RegistrationResponseDTO> response = new ArrayList<>();
        for(Registration reg : registrations) {
            if(reg.getStudentId().equals(studentId)) {
                Course course = courses.get(reg.getCourseId());
                response.add(new RegistrationResponseDTO(course.getCode(), course.getTitle(), reg.getRegistrationAt()));

                }
            }
        return response;
        }


    private void validateStudentAndCourse(UUID studentId, UUID courseId){
        if(!students.containsKey(studentId)) throw new CustomException("Student not found.");
        if(!courses.containsKey(courseId)) throw new CustomException("Course not found.");
    }
}

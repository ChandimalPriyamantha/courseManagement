package com.zdata.courseManagement.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Registration {

    private UUID studentId;
    private UUID courseId;
    private LocalDateTime registrationAt;

    public Registration(UUID studentId, UUID courseId, LocalDateTime registrationAt) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.registrationAt = registrationAt;
    }


}

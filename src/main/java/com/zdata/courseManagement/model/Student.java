package com.zdata.courseManagement.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Student {

    private UUID id;
    private String name;
    private String email;

    public Student(UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


}

package com.zdata.courseManagement.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Course {

    private UUID id;
    private String code;
    private String title;
    private String instruction;

    public Course(UUID id, String code, String title, String instruction) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.instruction = instruction;
    }


}

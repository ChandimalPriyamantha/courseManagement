package com.zdata.courseManagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    @NotBlank
    private String code;
    @NotBlank
    private String title;
    @NotBlank
    private String instructor;


}

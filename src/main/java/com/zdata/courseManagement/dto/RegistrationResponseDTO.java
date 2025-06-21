package com.zdata.courseManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponseDTO {

    private String courseCode;
    private String courseTitle;
    private LocalDateTime registeredAt;


}

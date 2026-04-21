package com.example.tareatime.model.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {

    private Integer id;
    private String title;
    private String description;
    private String professor;
    private LocalDate dueDate;
    private LocalTime dueTime;
    private Boolean status;
    private LocalDateTime createdAt;
}

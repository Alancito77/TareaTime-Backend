package com.example.tareatime.model.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TaskRequest {

    private String title;
    private String description;
    private String professor;
    private LocalDate dueDate;
    private LocalTime dueTime;
}
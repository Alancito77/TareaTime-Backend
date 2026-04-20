package com.example.tareatime.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Integer id;
    private String email;
    private LocalDateTime createdAt;

    private List<TaskResponse> tasks;
}
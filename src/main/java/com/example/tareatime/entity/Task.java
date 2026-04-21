package com.example.tareatime.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "professor", length = 100)
    private String professor;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "due_time")
    private LocalTime dueTime;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Relación: muchas tareas -> un usuario
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

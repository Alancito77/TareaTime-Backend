package com.example.tareatime.controller;

import com.example.tareatime.model.request.TaskRequest;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")

public class TaskController {

    @Autowired
    private ITaskService iTaskService;

    // CONTROLLER POST PARA CREAR TAREA
    @PostMapping("/usuario/{userId}")
    public ResponseEntity<TaskResponse> createTask(
            @PathVariable Integer userId,
            @RequestBody TaskRequest request) {

        TaskResponse response = iTaskService.createTask(userId, request);
        return ResponseEntity.ok(response);
    }
}
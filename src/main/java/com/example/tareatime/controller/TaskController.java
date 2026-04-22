package com.example.tareatime.controller;

import com.example.tareatime.model.request.TaskRequest;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        iTaskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    // ENDPOINT PARA OBTENER UNA SOLA TAREA POR ID
    @GetMapping("{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("id") Integer id) {
        TaskResponse response = iTaskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    // ENDPOINT PARA ACTUALIZAR UNA TAREA
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable("id") Integer id, @RequestBody TaskRequest request) {
        TaskResponse updatedTask = iTaskService.updateTask(id, request);
        return ResponseEntity.ok(updatedTask);
    }
}
package com.example.tareatime.service.imp;

import com.example.tareatime.entity.Task;
import com.example.tareatime.entity.User;
import com.example.tareatime.model.request.TaskRequest;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.repository.ITaskRepository;
import com.example.tareatime.repository.IUserRepository;
import com.example.tareatime.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskServiceImp implements ITaskService {

    @Autowired
    private ITaskRepository iTaskRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public TaskResponse createTask(Integer userId, TaskRequest request) {

        // BUSCAR USUARIO
        User user = iUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // CREAR TAREA
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .professor(request.getProfessor())
                .dueDate(request.getDueDate())
                .dueTime(request.getDueTime())
                .status(false) // pendiente por defecto
                .createdAt(LocalDateTime.now())
                .user(user)
                .build();

        // GUARDAR
        Task savedTask = iTaskRepository.save(task);

        // AQUIE SE CONVIERTE A RESPONSE
        return TaskResponse.builder()
                .id(savedTask.getId())
                .title(savedTask.getTitle())
                .description(savedTask.getDescription())
                .professor(savedTask.getProfessor())
                .dueDate(savedTask.getDueDate())
                .dueTime(savedTask.getDueTime())
                .status(savedTask.getStatus())
                .createdAt(savedTask.getCreatedAt())
                .build();
    }
}
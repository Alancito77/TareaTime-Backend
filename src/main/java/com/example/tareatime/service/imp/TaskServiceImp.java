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

    @Override
    public void deleteTask(Integer id) {
        // 1. Validar que la tarea exista
        if (!iTaskRepository.existsById(id)) {
            throw new RuntimeException("Error: La tarea con ID " + id + " no existe");
        }

        // 2. Eliminarla
        iTaskRepository.deleteById(id);
    }

    @Override
    public TaskResponse getTaskById(Integer id) {
        // 1. Buscamos la tarea en la base de datos
        Task task = iTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Error: La tarea con ID " + id + " no existe"));

        // 2. La convertimos a Response
        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .professor(task.getProfessor())
                .dueDate(task.getDueDate())
                .dueTime(task.getDueTime())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .build();
    }
    @Override
    public TaskResponse updateTask(Integer id, TaskRequest request) {
        // Buscamos la tarea actual
        Task task = iTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        // Actualizamos sus datos con lo que mandó Angular
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setProfessor(request.getProfessor());
        task.setDueDate(request.getDueDate());
        task.setDueTime(request.getDueTime());
        // El status y createdAt normalmente no se cambian al editar

        // Guardamos en la base de datos
        Task savedTask = iTaskRepository.save(task);

        // Retornamos la respuesta
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
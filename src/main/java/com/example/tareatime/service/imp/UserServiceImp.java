package com.example.tareatime.service.imp;

import com.example.tareatime.entity.Task;
import com.example.tareatime.entity.User;
import com.example.tareatime.model.request.UserRequest;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.model.response.UserResponse;
import com.example.tareatime.repository.IUserRepository;
import com.example.tareatime.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserResponse getUserById(Integer id) {

        User u = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<TaskResponse> tasks = new ArrayList<>();

        if (u.getTasks() != null) {
            for (Task t : u.getTasks()) {
                TaskResponse taskResponse = TaskResponse.builder()
                        .id(t.getId())
                        .title(t.getTitle())
                        .description(t.getDescription())
                        .professor(t.getProfessor())
                        .dueDate(t.getDueDate())
                        .dueTime(t.getDueTime())
                        .status(t.getStatus())
                        .createdAt(t.getCreatedAt())
                        .build();

                tasks.add(taskResponse);
            }
        }

        return UserResponse.builder()
                .id(u.getId())
                .email(u.getEmail())
                .createdAt(u.getCreatedAt())
                .tasks(tasks)
                .build();
    }

    @Override
    public List<TaskResponse> getTasksByUserId(Integer id) {

        User u = iUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<TaskResponse> tasks = new ArrayList<>();

        if (u.getTasks() == null || u.getTasks().isEmpty()) {
            return tasks;
        }

        for (Task t : u.getTasks()) {
            TaskResponse taskResponse = TaskResponse.builder()
                    .id(t.getId())
                    .title(t.getTitle())
                    .description(t.getDescription())
                    .professor(t.getProfessor())
                    .dueDate(t.getDueDate())
                    .dueTime(t.getDueTime())
                    .status(t.getStatus())
                    .createdAt(t.getCreatedAt())
                    .build();

            tasks.add(taskResponse);
        }

        return tasks;
    }

    @Override
    public UserResponse login(String email, String password) {

        User user = iUserRepository.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .tasks(new ArrayList<>()) // opcional
                .build();
    }

    @Override
    public UserResponse register(UserRequest request) {

        // VALIDAR SI YA EXISTE
        User existingUser = iUserRepository.findByEmail(request.getEmail());
        if (existingUser != null) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // CREAR USUARIO
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        // GUARDAR
        User savedUser = iUserRepository.save(user);

        // RETORNAR RESPONSE
        return UserResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .createdAt(savedUser.getCreatedAt())
                .tasks(null)
                .build();
    }
}

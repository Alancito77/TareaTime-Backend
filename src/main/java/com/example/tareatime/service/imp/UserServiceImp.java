package com.example.tareatime.service.imp;

import com.example.tareatime.entity.Task;
import com.example.tareatime.entity.User;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.model.response.UserResponse;
import com.example.tareatime.repository.IUserRepository;
import com.example.tareatime.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = iUserRepository.findAll();
        List<UserResponse> response = new ArrayList<>();

        for (User u : users) {

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

            UserResponse userResponse = UserResponse.builder()
                    .id(u.getId())
                    .email(u.getEmail())
                    .createdAt(u.getCreatedAt())
                    .tasks(tasks)
                    .build();

            response.add(userResponse);
        }

        return response;
    }

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
}

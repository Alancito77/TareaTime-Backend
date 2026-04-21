package com.example.tareatime.service;
import com.example.tareatime.model.request.UserRequest;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.model.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse getUserById(Integer id);
    UserResponse register(UserRequest request);
    List<TaskResponse> getTasksByUserId(Integer id);
    UserResponse login(String email, String password);
}

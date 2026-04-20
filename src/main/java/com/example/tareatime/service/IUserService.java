package com.example.tareatime.service;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.model.response.UserResponse;

import java.util.List;

public interface IUserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Integer id);
    List<TaskResponse> getTasksByUserId(Integer id);
    }
}

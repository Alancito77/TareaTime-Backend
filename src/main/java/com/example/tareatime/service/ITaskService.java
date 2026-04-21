package com.example.tareatime.service;

import com.example.tareatime.model.request.TaskRequest;
import com.example.tareatime.model.response.TaskResponse;

public interface ITaskService {

    TaskResponse createTask(Integer userId, TaskRequest request);
}
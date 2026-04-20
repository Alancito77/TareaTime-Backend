package com.example.tareatime.repository;

import com.example.tareatime.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {

    // Obtener tareas por usuario
    List<Task> findByUserId(Integer userId);
}

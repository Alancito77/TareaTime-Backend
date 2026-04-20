package com.example.tareatime.repository;

import com.example.tareatime.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}

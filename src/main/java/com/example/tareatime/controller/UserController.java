package com.example.tareatime.controller;

import com.example.tareatime.model.request.LoginRequest;
import com.example.tareatime.model.request.UserRequest;
import com.example.tareatime.model.response.TaskResponse;
import com.example.tareatime.model.response.UserResponse;
import com.example.tareatime.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inicio")
public class UserController {
        @Autowired
        private IUserService iUserService;

        // METODO GET PARA VER TAREAS
        @GetMapping("/usuario/{id}/tareas")
        public ResponseEntity<List<TaskResponse>> getTasksByUserId(@PathVariable Integer id) {
            return ResponseEntity.ok(iUserService.getTasksByUserId(id));
        }

        // ENDPOINT - POST PARA LOGGEAR
        @PostMapping("/login")
        public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(
                iUserService.login(request.getEmail(), request.getPassword())
            );
        }

        // ENDPOINT - REGISTRO DE USUARIO
        @PostMapping("/register")
        public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
            return ResponseEntity.ok(iUserService.register(request));
        }

        /*@PostMapping
        public ResponseEntity<MateriaResponse> saveSubject(@RequestBody MateriaRequest request) {
            MateriaResponse subject = iMateriaService.guardarMateria(request);
            return ResponseEntity.ok(subject);
        }

        @PutMapping
        public ResponseEntity<MateriaResponse> updateSubject(@RequestParam("id") Integer id, @RequestBody MateriaRequest request) {
            MateriaResponse subject = iMateriaService.actualizarMateria(id, request);
            return ResponseEntity.ok(subject);
        }

        @DeleteMapping
        public ResponseEntity<Void> deleteSubject(@RequestParam("id") Integer id) {
            iMateriaService.eliminarMateria(id);
            return ResponseEntity.noContent().build();
        }*/
}

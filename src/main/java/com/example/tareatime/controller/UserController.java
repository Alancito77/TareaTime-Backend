package com.example.tareatime.controller;

import com.example.tareatime.model.request.LoginRequest;
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

        @GetMapping("/usuario/{id}")
        public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
            return ResponseEntity.ok(iUserService.getUserById(id));
        }

        @PostMapping("/login")
        public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(
                iUserService.login(request.getEmail(), request.getPassword())
            );
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

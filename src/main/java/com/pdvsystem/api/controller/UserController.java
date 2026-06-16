package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.user.RegisterDTO;
import com.pdvsystem.api.domain.user.User;
import com.pdvsystem.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllClients() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody RegisterDTO body) {
        User user = userService.updateUser(id, body);
        return ResponseEntity.ok(user);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }
}

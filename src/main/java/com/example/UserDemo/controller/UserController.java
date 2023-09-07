package com.example.UserDemo.controller;

import com.example.UserDemo.model.User;
import com.example.UserDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/app")
@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable String name) {
        User user = userRepository.getUser(name);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<User>> getSortedUsers() {
        List<User> users = userRepository.getSortedUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userRepository.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/activity")
    public ResponseEntity<String> getActivityLog() {
        return ResponseEntity.ok("Activity log not implemented yet.");
    }
}

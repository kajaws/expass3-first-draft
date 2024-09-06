package com.example.Expass.controller;

import com.example.Expass.manager.PollManager;
import com.example.Expass.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private PollManager pollManager;

    @PostMapping
    public String createUser(@RequestBody User user) {
        pollManager.addUser(user);
        return "User created";
    }

    @GetMapping
    public String listUsers() {
        return pollManager.getAllUsers().toString();
    }

}
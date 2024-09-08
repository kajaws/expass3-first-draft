package com.example.Expass.controller;

import com.example.Expass.manager.PollManager;
import com.example.Expass.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PollManager pollManager;

    // Fetch all users
    @GetMapping
    public Collection<User> getAllUsers() {
        return pollManager.getUsers();
    }

    // Fetch a single user by ID
    @GetMapping("/{userId}")
    public User getUser(@PathVariable Long userId) {
        return pollManager.getUser(userId);
    }

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        pollManager.addUser(user.getUserId(), user);
        return user;
    }

    // Update an existing user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User updatedUser) {
        pollManager.addUser(userId, updatedUser);
        return updatedUser;
    }

    // Delete a user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        pollManager.removeUser(userId);
    }

}
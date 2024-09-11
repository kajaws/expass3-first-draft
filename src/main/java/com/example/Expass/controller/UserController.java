package com.example.Expass.controller;

import com.example.Expass.manager.PollManager;
import com.example.Expass.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PollManager pollManager;

    // Fetch all users
    @GetMapping
    public Collection<User> getAllUsers() {
        return pollManager.getUsers();
    }

    // Fetch a single user by username
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        User user = pollManager.getUserByUsername(username);
        if (user != null) {
            return user;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        String username = user.getUsername();

        if (pollManager.isUserExistByUsername(username)){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
        }

        pollManager.addUser(username, user);
        return user;
    }

    // Update an existing user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User updatedUser) {
        pollManager.addUser(userId, updatedUser);
        return updatedUser;
    }

    // Delete a user
    @DeleteMapping("/{username}")
    public void deleteUser(@PathVariable String username) {
        pollManager.removeUser(username);
    }

}
package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// Disable CORS policy
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
    @Autowired
    private UserService service;

    /* ENDPOINTS */

    /* POST */
    @PostMapping("/addUser")
    public String addNewUser(@RequestBody User newUser) {
        if (service.addUsers(newUser)) {
            return "Registered";
        }
        return "New user registration failed";
    }

    /* GET */

    @GetMapping("/users/{username}")
    public String getUserID(@PathVariable User user) {
        // Check User ID using service method

        // Return ID if found, else not found message

        return "Placeholder";
    }

    @GetMapping("/users/{username}/balance")
    public float getUserBalance(@PathVariable User user) {
        // Check User ID

        return service.getBalance(user);
    }
}

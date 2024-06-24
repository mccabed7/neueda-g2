package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.domain.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

// Allow CORS from named origin
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService service;

    /* ENDPOINTS */

    /* POST */

    @PostMapping("/api/addUser")
    public ResponseEntity<?> addNewUser(@RequestParam("newUser") User newUser) {
        if (service.addUsers(newUser)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/api/verify")
    public ResponseEntity<?> verifyUser(@RequestBody User user) {
        System.out.println(user.getUsername() + " " + user.getPassword());
        if (service.attemptLogin(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /* PUT */

    @PutMapping("/api/users")
    public ResponseEntity<?> updateBalance(@RequestParam String senderName,
            @RequestParam String recipientName,
            @RequestParam String amount) {

        User sender = service.getUser(senderName);
        User recipient = service.getUser(recipientName);

        // If users do not exist, respond 404
        if (sender == null || recipient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.sendMoney(sender, recipient, Float.parseFloat(amount));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* GET */

    @GetMapping("/api/users")
    public String getUserBalance(@RequestParam String username) {
        if (service.getUser(username) != null) {
            return Float.toString(service.getBalance(service.getUser(username)));
        }

        // Returning null if user not found
        return "User not found";
    }
}

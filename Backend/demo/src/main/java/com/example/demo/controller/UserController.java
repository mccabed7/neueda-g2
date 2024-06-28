package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

// Allow CORS from any origin
@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService service;

    /* ENDPOINTS */

    /* POST */

    /* Add user, ensuring "username" and "password" are sent in as JSON */
    @PostMapping("/api/addUser")
    public ResponseEntity<?> addNewUser(@RequestParam("newUser") User newUser) {
        if (service.addUsers(newUser)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* Verify username and password against list/database */
    @PostMapping("/api/verify")
    public ResponseEntity<?> verifyUser(@RequestBody User user) {
        System.out.println(user.getUsername() + " " + user.getPassword());
        if (service.attemptLogin(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /* PUT */

    /* Facilitates sending of money from one user to another */
    /* Takes sender username, recipient username and amount to send from sender */
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

    /* Gets a user's balance from their username in params */
    @GetMapping("/api/users")
    public ResponseEntity<?> getUserBalance(@RequestParam String username) {
        if (service.getUser(username) != null) {
            float balance = service.getBalance(service.getUser(username));
            return new ResponseEntity<>(balance, HttpStatus.OK);
        }

        // Returning null if user not found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
     * Gets ALL users and their balance
     * Response is in JSON format
     */
    @GetMapping(path = "/api/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        Iterable<User> userList = service.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}

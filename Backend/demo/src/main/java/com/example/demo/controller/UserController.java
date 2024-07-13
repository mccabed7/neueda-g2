package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.domain.User;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// Allow CORS from any origin
@CrossOrigin
@RestController
@Transactional
public class UserController {
    @Autowired
    private UserService service;

    /* ENDPOINTS */

    /* POST */

    /* Add user, ensuring "username" and "password" are sent in as JSON */
    @Operation(summary = "Add new user", description = "Adds a new user to the system given username and password")
    @PostMapping("/api/addUser")
    public ResponseEntity<?> addNewUser(@RequestParam("newUser") User newUser) {
        if (service.addUsers(newUser)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /* Verify username and password against list/database */
    @Operation(summary = "Login with user details", description = "Returns ok if valid username and password")
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
    @Operation(summary = "Send money", description = "Send money from one account to another")
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

        boolean requestSuccess = service.sendMoney(sender, recipient, Float.parseFloat(amount));
        System.out.println("TRANSACTION SUCCESS: " + sender.getUserBalance() + " " + requestSuccess);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /* GET */

    /* Gets a user's balance from their username in params */
    @Operation(summary = "Gets user balance", description = "Gets user balance")
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
    @Operation(summary = "Get all users", description = "Returns all users and their balances")
    @GetMapping(path = "/api/users/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        Iterable<User> userList = service.getAllUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}

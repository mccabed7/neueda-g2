package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Adds given User Object to repository
     * 
     * @param user User object containing at least a name and password. Balance
     *             optional.
     * @return True if added successfully. False if failed, or if name already in
     *         use.
     */
    public boolean addUsers(User user) {
        if (userRepository.findByName(user.getUsername()) != null) {
            userRepository.addUser(user);
            return true;
        }
        return false;
    }

    /**
     * Attempt login given a user object
     * 
     * @param user User object containing at least a name and password. Balance not
     *             needed.
     * @return True if login successful. False otherwise.
     */
    public boolean attemptLogin(User user) {
        User foundUser = userRepository.findByName(user.getUsername());
        if (foundUser == null)
            return false;
        if (foundUser.getPassword().equals(user.getPassword()))
            return true;
        return false;
    }

    /**
     * Send an amount of money from one user to another.
     * 
     * @param fromUser User object containing at least a name.
     * @param toUser   User object containing at least a name.
     * @param amount   Float amount to send.
     * @return True if sent successfully. False if failed recipient not found.
     */
    public boolean sendMoney(User fromUser, User toUser, float amount) {
        User foundFrom = userRepository.findByName(fromUser.getUsername());
        User foundTo = userRepository.findByName(toUser.getUsername());
        if (foundFrom == null || foundTo == null)
            return false;
        if (foundFrom.getUserBalance() < amount)
            return false;
        foundTo.setUserBalance(foundTo.getUserBalance() - amount);
        foundFrom.setUserBalance(foundFrom.getUserBalance() + amount);
        return true;
    }

    /**
     * Attempt login given a user object
     * 
     * @param user User object containing at least a name. Balance not needed.
     * @return Float with user balance.
     */
    public float getBalance(User user) {
        return (float) user.getUserBalance();
    }

    public User getUser(String name) {
        return userRepository.findByName(name);
    }
}

package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private static ArrayList<User> userList;

    public UserRepository() {
        userList = new ArrayList<User>();
    }

    // Returns User object, or null if not found
    public User findByName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(userName)) {
                return userList.get(i);
            }
        }
        return null;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    // Returns all users and balances as a map
    public Map<String, String> getAllUsers() {
        Map<String, String> map = new HashMap<>();

        // Iterate through userList, add info to map
        // "username" : "balance"
        for (User user : userList) {
            map.put(user.getUsername(), Double.toString(user.getUserBalance()));
        }

        return map;
    }
}

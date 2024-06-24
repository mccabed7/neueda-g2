package com.example.demo.service;


import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public  boolean addUsers(User user)
    {
        if(userRepository.findByName(user.getUsername()) != null){
            userRepository.addUser(user);
            return true;
        }
        return false;
    }

    public boolean attemptLogin(String username, String password){
        /*
        if(userRepository.findUser(username
         */
        return false;
    }


    public float getBalance(User user){
        return (float)user.getUserBalance();
    }


}

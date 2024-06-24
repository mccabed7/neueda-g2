package com.example.demo.service;


import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public  boolean addUsers(String userName, String password)
    {
        if(userRepository.findByName(userName) != null){
            userRepository.addUser(userName,password);
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


}

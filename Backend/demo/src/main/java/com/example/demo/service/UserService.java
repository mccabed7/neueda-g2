package com.example.demo.service;


import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    UserRepository userRepository = new UserRepository();

    public boolean addUsers(User user)
    {
        /*
        if(userRepository.existsByName(user.getUserKey()))
        {
            return false;
        }
        */
        //userRepository.insert(user);
        return true;
    }

    public float getBalance(User user){
        
    }


}

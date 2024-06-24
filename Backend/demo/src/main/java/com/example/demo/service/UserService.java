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



    public boolean attemptLogin(User user){
        User foundUser = userRepository.findByName(user.getUsername());
        if (foundUser == null) return false;
        if (foundUser.getPassword() == user.getPassword()) return true;
        return false;
    }

    public boolean sendMoney(User fromUser, User toUser, float amount){
        User foundFrom = userRepository.findByName(fromUser.getUsername());
        User foundTo = userRepository.findByName(toUser.getUsername());
        if(foundFrom == null || foundTo == null) return false;
        if(foundFrom.getUserBalance() < amount) return false;
        foundTo.setUserBalance(foundTo.getUserBalance() - amount);
        foundFrom.setUserBalance(foundFrom.getUserBalance() + amount);
        return true;
    }


    public float getBalance(User user){
        return (float)user.getUserBalance();
    }


}

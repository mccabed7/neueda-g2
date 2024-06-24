package com.example.demo.repository;
import java.util.ArrayList;
import com.example.demo.domain.User;

public class UserRepository {
    private ArrayList<User> userList;

    public UserRepository(){
        userList = new ArrayList<User>();
    }

    //Returns User object, or null if not found
    public User findByName(String userName){
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUsername().equals(userName)){
                return userList.get(i);
            }
        }
        return null;
    }
}

package com.example.demo.domain;

import jakarta.persistence.*;
//import org.hibernate.annotations.Table;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifier;
    private String username;
    private String password;
    private float userBalance;


    public User(){}

    public User(String username, String password, float userBalance ) {
        this.username = username;
        this.password = password;
        this.userBalance = userBalance;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long id) {
        this.identifier = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(float userBalance) {
        this.userBalance = userBalance;
    }
    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", bankBalance=" + userBalance + '}';
    }

    public static void main(String[] args) {
        User user = new User("john_doe", "securepassword", 1000.50f);
        System.out.println(user);

        user.setUsername("jane_doe");
        user.setPassword("newpassword");
        user.setUserBalance(2000.75f);


        System.out.println(user);
    }
}

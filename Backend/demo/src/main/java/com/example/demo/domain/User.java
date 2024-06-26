package com.example.demo.domain;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private double userBalance;


    public User(String username, String password, double userBalance ) {
        this.username = username;
        this.password = password;
        this.userBalance = userBalance;

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

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }
    @Override
    public String toString() {
        return "User{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", bankBalance=" + userBalance + '}';
    }

    public static void main(String[] args) {
        User user = new User("john_doe", "securepassword", 1000.50);
        System.out.println(user);

        user.setUsername("jane_doe");
        user.setPassword("newpassword");
        user.setUserBalance(2000.75);


        System.out.println(user);
    }
}

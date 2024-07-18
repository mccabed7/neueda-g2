package com.example.demo.domain;
import java.time.LocalDateTime;

public class Transaction {
    private User sendUser;
    private User recieveUser;
    private double amount;
    private LocalDateTime timestamp;

    public Transaction(User sendUser, User recieveUser, double amount, LocalDateTime timestamp) {
        this.sendUser = sendUser;
        this.recieveUser = recieveUser;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }
    public User getSendUser() {
        return sendUser;
    }
    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }
    public User getRecieveUser() {
        return recieveUser;
    }
    public void setRecieveUser(User recieveUser) {
        this.recieveUser = recieveUser;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "fromUser=" + sendUser.getUsername() +
                ", toUser=" + recieveUser.getUsername() +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}

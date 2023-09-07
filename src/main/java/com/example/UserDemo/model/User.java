package com.example.UserDemo.model;

public class User {

    private String name;
    private int age;
    private double accountBalance;
    private String location;

    public User(){

    }

    public User(String name, int age, double accountBalance, String location) {
        this.name = name;
        this.age = age;
        this.accountBalance = accountBalance;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}


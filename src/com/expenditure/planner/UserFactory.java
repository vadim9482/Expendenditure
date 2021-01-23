package com.expenditure.planner;

public class UserFactory {

    public User createUser(String name, String password) {
        return new User(name, password);
    }

    public User createUser(String ID, String name, String password) {
        return new User(ID, name, password);
    }
}

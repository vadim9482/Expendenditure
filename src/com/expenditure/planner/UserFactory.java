package com.expenditure.planner;

public class UserFactory {

    public static User createUser(String name, String password) {
        return new User(name, password);
    }
}

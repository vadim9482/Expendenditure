package com.expenditure.planner;

public class DataBase {
    String name;
    String password;

    public DataBase(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}

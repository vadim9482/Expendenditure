package com.expenditure.planner;

public class Admin {
    String name;
    String password;
    String url;

    public Admin(String name, String password) {
        super();
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

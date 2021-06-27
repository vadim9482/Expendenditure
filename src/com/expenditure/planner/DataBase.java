package com.expenditure.planner;

public class DataBase {
    String name;
    String password;
    Admin admin;

    public DataBase(String name, String password, Admin admin) {
        super();
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Admin getAdmin() {
        return admin;
    }
}

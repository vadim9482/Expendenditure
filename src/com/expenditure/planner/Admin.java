package com.expenditure.planner;

public class Admin {
    String name;
    String password;
    String url;

    public Admin(String name, String password, String url) {
        super();
        this.name = name;
        this.password = password;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

}

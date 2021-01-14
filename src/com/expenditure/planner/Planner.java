package com.expenditure.planner;

import java.util.HashMap;
import java.util.Map;

import com.expenditure.planner.dataCenter.DAO;

public class Planner {
    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String SEPARATOR = ",";
    public static final String DATE_FORMAT = "dd-MMM-yy";
    public static final String MONEY_FORMAT = "0.00";

    public static final String URL_DATABASE = "jdbc:postgresql://localhost/expendenditure";
    public static final String LOGIN_DATABASE = "expendenditureadmin";
    public static final String PASS_DATABASE = "pass";
    private Map<String, User> users = new HashMap<>();
    DAO dao = new DAO();

    public void createUser(String name, String password) {
        if (!users.containsKey(name)) {
            User user = new User(name, password);
            users.put(user.getName(), user);
            
        }
    }

    private User getUser(String name) {
        if (!users.containsKey(name)) {
            throw new IllegalArgumentException("No user " + name + " was found");
        }
        return users.get(name);
    }

    public void importCSVPlan(String name, String path, String fileName) {
        User user = getUser(name);
        user.addPlan(dao.importPaymentCsv(path, fileName));
        System.out.println("Plans for " + name + " was exported");
    }
}

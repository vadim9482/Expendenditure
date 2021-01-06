package com.expenditure.planner;

import com.expenditure.planner.dataCenter.JDBCPSQL;

public class Planner {
    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String RESOURCE_PATH = "resources";
    public static final String SEPARATOR = ",";
    public static final String DATE_FORMAT = "dd-MMM-yy";
    public static final String MONEY_FORMAT = "0.00";
    public static final String EXPENCES_FILENAME = "expences.txt";
    public static final String CARD_EXPENCES_FILENAME = "cardExpences.txt";
    public static final String PLANS_FILENAME = "plans.txt";
    public static final String URL_DATABASE = "jdbc:postgresql://localhost/transactions";
    public static final String LOGIN_DATABASE = "expendenditureadmin";
    public static final String PASS_DATABASE = "admin";

    public void createUser(String name, String password) {
        JDBCPSQL jdbcPostgresSQL = new JDBCPSQL();
        System.out.println(jdbcPostgresSQL.connectCheck());
        jdbcPostgresSQL.createUserTable(name);
        User user = new User(name, password);
        // user.importCSVPlan(PLANS_FILENAME);
    }

    public void importCSVPlan(String filePath) {
    }
}

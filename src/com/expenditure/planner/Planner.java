package com.expenditure.planner;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOUser;

public class Planner {
    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String SEPARATOR = ",";
    public static final String DATE_FORMAT = "dd-MMM-yy";
    public static final String MONEY_FORMAT = "0.00";
    public static final String URL_DATABASE = "jdbc:postgresql://localhost/expendenditure";
    public static final String LOGIN_DATABASE = "expendenditureadmin";
    public static final String PASS_DATABASE = "pass";
    public static final String RESOURCE_PATH = "resources";
    public static final String EXPENCES_FILENAME = "expences.csv";
    public static final String CARD_EXPENCES_FILENAME = "cardExpences.csv";
    public static final String PLANS_FILENAME = "plans.csv";

    public void start() {
        User user = UserFactory.createUser("vadim9482", "pass");
        DAO<User> daoUser = new DAOUser();
        daoUser.get(CARD_EXPENCES_FILENAME);
    }
}

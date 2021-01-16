package com.expenditure.planner;

import java.util.List;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOUser;
import com.expenditure.planner.formatter.Formater;
import com.expenditure.planner.formatter.FormaterUsers;

public class Planner {
    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String SEPARATOR = ",";
    public static final String DATE_FORMAT = "dd-MMM-yy";
    public static final String MONEY_FORMAT = "0.00";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/expendenditure";
    public static final String DATABASE_LOGIN = "expendenditureadmin";
    public static final String DATABASE_PASS = "pass";
    public static final String RESOURCE_PATH = "resources";
    public static final String EXPENCES_FILENAME = "expences.csv";
    public static final String CARD_EXPENCES_FILENAME = "cardExpences.csv";
    public static final String PLANS_FILENAME = "plans.csv";
    public static final String USER_NAME = "vadim9482";
    public static final String USER_PASSWORD = "pass";

    public void showUserAll() {
        DAO<User> daoUser = new DAOUser();
        List<User> users = daoUser.getAll();
        Formater<User> formater = new FormaterUsers();
        System.out.println(formater.toString(users));
    }

    public void importDB() {
        UserFactory userFactory = new UserFactory();
        User user = userFactory.createUser(USER_NAME, USER_PASSWORD);
        DAO<User> daoUser = new DAOUser();
        daoUser.save(user);
        daoUser.get(USER_NAME);
    }
}

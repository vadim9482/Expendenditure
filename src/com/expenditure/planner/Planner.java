package com.expenditure.planner;

import java.util.List;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOUser;
import com.expenditure.planner.dao.postgres.TableFactory;
import com.expenditure.planner.formatter.Formater;
import com.expenditure.planner.formatter.FormaterUsers;

public class Planner {
    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String SEPARATOR = ",";
    public static final String FORMAT_DATE = "dd-MMM-yy";
    public static final String FORMAT_MONEY = "0.00";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/expendenditure";
    public static final String DATABASE_LOGIN = "expendenditureadmin";
    public static final String DATABASE_PASS = "pass";
    public static final String RESOURCE_PATH = "resources";
    public static final String FILENAME_EXPENCES = "expences.csv";
    public static final String FILENAME_CARD_EXPENCES = "cardExpences.csv";
    public static final String FILENAME_PLANS = "plans.csv";

    public void initDB() {
        TableFactory tableFactory = new TableFactory();
        tableFactory.createUsersTable();
        tableFactory.createPlansTable();
        tableFactory.createCashTable();
        tableFactory.createCardTable();
    }

    public void showUserAll() {
        DAO<User> daoUser = new DAOUser();
        List<User> users = daoUser.getAll();
        Formater<User> formater = new FormaterUsers();
        System.out.println(formater.toString(users));
    }

    public User getUser(String name, String password) {
        DAO<User> daoUser = new DAOUser();
        User user = daoUser.get(name).get();
        return user;
    }

    public void newUser(String name, String password) {
        User user = new UserFactory().createUser(name, password);
        DAO<User> daoUser = new DAOUser();
        daoUser.save(user);
    }
}

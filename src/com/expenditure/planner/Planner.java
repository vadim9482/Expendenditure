package com.expenditure.planner;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOPayment;
import com.expenditure.planner.dao.DAOTransation;
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
        System.out.println(formater.listToString(users));
    }

    public User readUser(String name) {
        User user = null;
        DAO<User> daoUser = new DAOUser();
        Optional<User> optional = daoUser.get(name);
        if (optional != null) {
            user = optional.get();
        } else {
            throw new IllegalArgumentException("Wrong user");
        }
        DAO<Payment> daoPayment = new DAOPayment();
        return user;
    }

    public User createUser(String name, String password) {
        User user = new UserFactory().createUser(name, password);
        return user;
    }
    
    public void saveUser(User user) {
        DAO<User> daoUser = new DAOUser();
        DAO<Payment> daoPayment = new DAOPayment();
        DAO<Transaction> daoTransaction = new DAOTransation();
        daoUser.save(user);
       // daoPayment.saveAll(user.getPlans());
       // daoTransaction.saveAll(user.getCash());
    }
}

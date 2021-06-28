package com.expenditure.planner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOAdmin;
import com.expenditure.planner.dao.DAODataBase;
import com.expenditure.planner.dao.DAOUser;
import com.expenditure.planner.dao.postgres.TableFactory;

/*
import com.expenditure.planner.dao.DAOPayment;
import com.expenditure.planner.dao.DAOTransation;
import com.expenditure.planner.dao.csv.DAOCSVPayment;
import com.expenditure.planner.dao.csv.DAOCSVTransaction;
import com.expenditure.planner.dao.postgres.TableFactory;
*/
public class Planner {

    public static final String SUPER_DATABASE_URL = "jdbc:postgresql://localhost/";
    public static final String SUPER_DATABASE_NAME = "postgres";
    public static final String SUPER_DATABASE_PASS = "postgres";

    public static final String ADMIN_NAME = "adminvadim";
    public static final String ADMIN_PASSWORD = "password";

    public static final String ADMIN2_NAME = "adminirina";
    public static final String ADMIN2_PASSWORD = "password";

    public static final String ADMIN3_NAME = "adminyana";
    public static final String ADMIN3_PASSWORD = "password";

    public static final String DATABASE_NAME = "expendenditure";
    public static final String DATABASE_PASSWORD = "password";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost/" + DATABASE_NAME;

    public static final String USER_NAME = "Vadim";
    public static final String USER_PASSWORD = "password";

    public static final String USER2_NAME = "Yana";
    public static final String USER2_PASSWORD = "password";

    public static final String TABLE_USERS = "users";
    public static final String TABLE_PAYMENTS = "payments";
    public static final String TABLE_TRANSACTIONS = "transactions";

    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String SEPARATOR_CSV = ",";
    public static final String FORMAT_DATE = "dd-MMM-yy";
    public static final String FORMAT_MONEY = "0.00";
    public static final String RESOURCE_PATH = "resources";
    public static final String FILENAME_EXPENCES = "expences.csv";
    public static final String FILENAME_CARD_EXPENCES = "cardExpences.csv";
    public static final String FILENAME_PLANS = "plans.csv";

    public void adminDataBaseTest() {
        Admin admin = new Admin(ADMIN_NAME, ADMIN_PASSWORD);
        Admin admin2 = new Admin(ADMIN2_NAME, ADMIN2_PASSWORD);
        Admin admin3 = new Admin(ADMIN3_NAME, ADMIN3_PASSWORD);
        List<Admin> admins = new LinkedList<>();
        admins.add(admin);
        admins.add(admin2);
        DAO<Admin> daoAdmin = new DAOAdmin();
        daoAdmin.saveAll(admins);
        daoAdmin.update(admin2, new String[] { admin3.getName(), admin3.getPassword() });
        DataBase dataBase = new DataBase(DATABASE_NAME, DATABASE_PASSWORD, admin);
        DAO<DataBase> daoDataBase = new DAODataBase();
        daoDataBase.save(dataBase);
        TableFactory tableFactory = new TableFactory();
        tableFactory.createTableUsers();
        tableFactory.createTablePlans();
        tableFactory.createTableCard();
        tableFactory.createTableCash();
        DAO<User> daoUser = new DAOUser();
        User user = new User(USER_NAME, USER_PASSWORD);
        daoUser.save(user);

        Optional<User> userOptional = daoUser.get(USER_NAME);
        User userDB = null;
        if (userOptional != null) {
            userDB = userOptional.get();
            System.out.println(userDB.getName());
        } else {
            System.out.println("USER: " + USER2_NAME + " doesn't exist");
        }

        daoUser.delete(user);
        tableFactory.deleteTableCash();
        tableFactory.deleteTableCard();
        tableFactory.deleteTablePlans();
        tableFactory.deleteTableUsers();
        daoDataBase.delete(dataBase);
        daoAdmin.delete(admin);
        daoAdmin.delete(admin2);
        daoAdmin.delete(admin3);
    }
    /*
     * public void testInitDB() { TableFactory tableFactory = new TableFactory();
     * tableFactory.createUsersTable(); tableFactory.createPlansTable();
     * tableFactory.createCashTable(); tableFactory.createCardTable(); }
     * 
     * public void testShowUserAll() { DAO<User> daoUser = new DAOUser(); List<User>
     * users = daoUser.getAll(); System.out.println(users);
     * 
     * 
     * }
     * 
     * public User testReadUser(String name) { User user = null; DAO<User> daoUser =
     * new DAOUser(); Optional<User> optional = daoUser.get(name); if (optional !=
     * null) { user = optional.get(); } else { throw new
     * IllegalArgumentException("Wrong user"); } DAO<Payment> daoPayment = new
     * DAOPayment(); return user; }
     * 
     * public User testCreateUser(String name, String password) { User user = new
     * UserFactory().createUser(name, password); return user; }
     * 
     * public void testSaveUser(User user) { DAO<User> daoUser = new DAOUser();
     * DAO<Payment> daoPayment = new DAOPayment(); DAO<Transaction> daoTransaction =
     * new DAOTransation(); daoUser.save(user); //
     * daoPayment.saveAll(user.getPlans()); //
     * daoTransaction.saveAll(user.getCash()); }
     * 
     * public void testNewPayments() { User user = testCreateUser("Vadim", "pass");
     * DAO<Payment> daoCSVPayment = new DAOCSVPayment(); ListOfPayments
     * listOfPayments = new ListOfPayments("plans");
     * listOfPayments.add(daoCSVPayment.getAll());
     * user.addPaymentTable(listOfPayments); ListOfPayments listOfPaymentsOut =
     * user.getListOfPayments().get("plans");
     * System.out.println(listOfPaymentsOut.toString());
     * System.out.println(listOfPaymentsOut.getSum().toString()); }
     * 
     * public void testNewTransaction() { User user = testCreateUser("Vadim",
     * "pass"); DAO<Transaction> daoTransaction = new DAOCSVTransaction();
     * ListOfTransactions listOfTransactions = new ListOfTransactions("cash");
     * listOfTransactions.add(daoTransaction.getAll());
     * user.addTransactionTable(listOfTransactions); ListOfTransactions
     * listOfTransactionOut = user.getListOfTransactions().get("cash");
     * System.out.println(listOfTransactionOut.toString());
     * System.out.println(listOfTransactionOut.getSum().toString()); }
     */
}

package com.expenditure.planner;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOAdmin;
import com.expenditure.planner.dao.DAOPayment;
import com.expenditure.planner.dao.DAOTransation;
import com.expenditure.planner.dao.DAOUser;
import com.expenditure.planner.dao.csv.DAOCSVPayment;
import com.expenditure.planner.dao.csv.DAOCSVTransaction;
import com.expenditure.planner.dao.postgres.TableFactory;

public class Planner {

    public static final String SUPER_ADMIN_URL = "jdbc:postgresql://localhost/";
    public static final String SUPER_ADMIN_NAME = "postgres";
    public static final String SUPER_ADMIN_PASS = "postgres";

    public static final String ADMIN_NAME = "adminvadim";
    public static final String ADMIN_PASSWORD = "password";

    public static final String ADMIN2_NAME = "adminirina";
    public static final String ADMIN2_PASSWORD = "password";

    public static final String ADMIN3_NAME = "adminyana";
    public static final String ADMIN3_PASSWORD = "password";

    public static final String ADMIN_DATABASE_LOGIN = "expendenditureadmin";
    public static final String ADMIN_DATABASE_PASS = "password";
    public static final String ADMIN_DATABASE_URL = "jdbc:postgresql://localhost/expendenditure";

    public static final String USER_NAME = "Vadim";
    public static final String USER_PASSWORD = "pass";

    public static final String SEPARATOR_LINE = System.lineSeparator();
    public static final String SEPARATOR = ",";
    public static final String FORMAT_DATE = "dd-MMM-yy";
    public static final String FORMAT_MONEY = "0.00";
    public static final String RESOURCE_PATH = "resources";
    public static final String FILENAME_EXPENCES = "expences.csv";
    public static final String FILENAME_CARD_EXPENCES = "cardExpences.csv";
    public static final String FILENAME_PLANS = "plans.csv";

    public static final String DATABASE_NAME = "expendediture";

    public void adminTest() {
        Admin admin = new Admin(ADMIN_NAME, ADMIN_PASSWORD, SUPER_ADMIN_URL);
        Admin admin2 = new Admin(ADMIN2_NAME, ADMIN2_PASSWORD, SUPER_ADMIN_URL);
        Admin admin3 = new Admin(ADMIN3_NAME, ADMIN3_PASSWORD, SUPER_ADMIN_URL);
        List<Admin> admins = new LinkedList<>();
        admins.add(admin);
        admins.add(admin2);
        DAO<Admin> daoAdmin = new DAOAdmin();

        daoAdmin.saveAll(admins);
        daoAdmin.update(admin2, new String[] { admin3.getName(), admin3.getPassword() });
        
        daoAdmin.delete(admin);
        daoAdmin.delete(admin2);
        daoAdmin.delete(admin3);
        /*
         * DataBase dataBase = new DataBase(dataBaseName, admin.getName());
         * DAO<DataBase> daoDataBase = new DAODataBase(); daoDataBase.save(dataBase);
         * daoDataBase.delete(dataBase); UserFactory userFactory = new UserFactory();
         * User user = userFactory.createUser(userName, password); DAO<User> daoUser =
         * new DAOUser(); daoUser.save(user); daoUser.delete(user); DAO<Payment>
         * daoPayment = new DAOCSVPayment(); ListOfPayments listOfPayments = new
         * ListOfPayments(plans); listOfPayments.add(daoPayment.getAll());
         * user.addPaymentTable(listOfPayments);
         * System.out.println(user.getPayments(plans)); JDBCPSQLTables jdbcpsqlTables =
         * new JDBCPSQLTables(); JDBCPSQLPayment jdbcpsqlPayment = new
         * JDBCPSQLPayment(); JDBCPSQLUser jdbcpsqlUser = new JDBCPSQLUser(); User
         * userDB = jdbcpsqlUser.getUser(userName).get();
         * jdbcpsqlTables.appendTableInfo(user.getPayments(plans).getUuid().toString(),
         * user.getPayments(plans).getName(), userDB.getUuid().toString()); //
         * jdbcpsqlPayment.savePayment(user.getPayments(plans).getList(), plans);
         */
    }

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
        System.out.println(users);
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

    public void testNewPayments() {
        User user = createUser("Vadim", "pass");
        DAO<Payment> daoCSVPayment = new DAOCSVPayment();
        ListOfPayments listOfPayments = new ListOfPayments("plans");
        listOfPayments.add(daoCSVPayment.getAll());
        user.addPaymentTable(listOfPayments);
        ListOfPayments listOfPaymentsOut = user.getListOfPayments().get("plans");
        System.out.println(listOfPaymentsOut.toString());
        System.out.println(listOfPaymentsOut.getSum().toString());
    }

    public void testNewTransaction() {
        User user = createUser("Vadim", "pass");
        DAO<Transaction> daoTransaction = new DAOCSVTransaction();
        ListOfTransactions listOfTransactions = new ListOfTransactions("cash");
        listOfTransactions.add(daoTransaction.getAll());
        user.addTransactionTable(listOfTransactions);
        ListOfTransactions listOfTransactionOut = user.getListOfTransactions().get("cash");
        System.out.println(listOfTransactionOut.toString());
        System.out.println(listOfTransactionOut.getSum().toString());
    }
}

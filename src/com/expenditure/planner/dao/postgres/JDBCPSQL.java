package com.expenditure.planner.dao.postgres;

import com.expenditure.planner.Payment;
import com.expenditure.planner.Transaction;
import com.expenditure.planner.User;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Connection;

import static com.expenditure.planner.Planner.URL_DATABASE;
import static com.expenditure.planner.Planner.LOGIN_DATABASE;
import static com.expenditure.planner.Planner.PASS_DATABASE;

public class JDBCPSQL {

    Logger logger = Logger.getLogger(JDBCPSQL.class.getName());

    public void addUser(User user) {
        TableFactory.createUsersTable();
        TableFactory.createPlansTable(user.getName());
        TableFactory.createCashTable(user.getName());
        TableFactory.createCardTable(user.getName());
        String query = "INSERT INTO users (ID, NAME, PASSWORD) VALUES (?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            if (!isAvailabeUser(user.getName())) {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user.getUuid().toString());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.executeUpdate();
                logger.info("User " + user.getName() + " was appended");
            } else {
                logger.info("User " + user.getName() + " already exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isAvailabeUser(String name) {
        String query = "SELECT * FROM users WHERE NAME='" + name + "';";
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            flag = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void appendPlan(Payment payment) {

        String query = "INSERT INTO payments(NAME, VALUE) VALUES(?, ?)";
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, payment.getName());
            preparedStatement.setInt(1, payment.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void appendPlan(List<Payment> listPayments, String name) {
        String query = "INSERT INTO " + name + "_payments (ID, NAME, PAYMENT_VALUE) VALUES (?,?,?)";
        int i = 0;
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Payment payment : listPayments) {
                preparedStatement.setString(1, payment.getID().toString());
                preparedStatement.setString(2, payment.getName());
                preparedStatement.setInt(3, payment.getValue());
                preparedStatement.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " Rows were appended");
    }

    public void appendCash(List<Transaction> transactions) {
        String query = "INSERT INTO transactions (ID, NAME, PAYMENT_VALUE) VALUES (?,?,?)";
        int i = 0;
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Transaction transaction : transactions) {
                preparedStatement.setString(1, transaction.getID().toString());
                preparedStatement.setString(2, transaction.getName());
                preparedStatement.setInt(3, transaction.getValue());
                preparedStatement.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " Rows were appended");
    }

    public boolean connectCheck() {
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            Statement statement = connection.createStatement();
            if (connection != null) {
                System.out.println("Connected to PostgreSQL server");
                flag = true;
            }
            ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Payment> returnAllPlans(String name) {
        List<Payment> listPayments = new ArrayList<>();
        String query = "SELECT * FROM PAYMENTS;";
        int i = 0;
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listPayments.add(new Payment(resultSet.getString(2), resultSet.getInt(3)));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i + " Rows were readed");
        return listPayments;
    }

    public void createAdminDB(String name, String password) {
        String query = "CREATE USER " + name + "WITH " + "CREATEDB PASSWORD " + "'" + password + "';" + "SET ROLE "
                + name + ";" + "CREATE DATABASE " + "PAYMENTS" + name + " OWNER " + name + ";";
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

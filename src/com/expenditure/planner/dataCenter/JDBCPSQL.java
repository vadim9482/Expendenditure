package com.expenditure.planner.dataCenter;

import com.expenditure.planner.ListPayments;
import com.expenditure.planner.Payment;
import com.expenditure.planner.Transaction;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import static com.expenditure.planner.Planner.URL_DATABASE;
import static com.expenditure.planner.Planner.LOGIN_DATABASE;
import static com.expenditure.planner.Planner.PASS_DATABASE;

public class JDBCPSQL {

    public void createUsersTable() {
        String tableName = "users";
        String query = "CREATE TABLE " + tableName
                + " (ID VARCHAR(36) NOT NULL, NAME VARCHAR(128) NOT NULL, PASSWORD VARCHAR(128) NOT NULL);";
        createTable(tableName, query);
    }

    public void createUserPlansTable(String name) {
        String tableName = name + "_plans";
        String query = "CREATE TABLE " + tableName
                + " (ID VARCHAR(36) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT);";
        createTable(tableName, query);
    }

    public void createUserCashTable(String name) {
        String tableName = name + "_cash";
        String query = "CREATE TABLE " + tableName
                + " (ID VARCHAR(128) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    public void createUserCardTable(String name) {
        String tableName = name + "_card";
        String query = "CREATE TABLE " + tableName
                + " (ID VARCHAR(128) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    private void createTable(String tableName, String query) {
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                System.out.println("Table " + tableName + " already exist");
            } else {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                System.out.println("Table " + tableName + " was created");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String ID, String name, String password) {
        createUsersTable();

        String query = "INSERT INTO users (ID, NAME, PASSWORD) VALUES (?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User " + name + " was appended");
    }

    
    //SELECT * FROM users WHERE NAME='vadim9482';
    
    
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

    public void appendPlan(ListPayments listPayments, String name) {
        String query = "INSERT INTO " + name + "_payments (ID, NAME, PAYMENT_VALUE) VALUES (?,?,?)";
        int i = 0;
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Payment payment : listPayments.getPayments()) {
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

    public ListPayments returnListPlans(String name) {
        ListPayments listPayments = new ListPayments();
        String query = "SELECT * FROM " + name + "_PAYMENTS;";
        int i = 0;
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listPayments.addPayment(new Payment(resultSet.getString(2), resultSet.getInt(3)));
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

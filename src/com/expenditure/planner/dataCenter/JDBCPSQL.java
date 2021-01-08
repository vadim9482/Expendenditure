package com.expenditure.planner.dataCenter;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.expenditure.planner.ListPayments;
import com.expenditure.planner.Payment;
import com.expenditure.planner.Transaction;

import java.sql.Connection;
import static com.expenditure.planner.Planner.URL_DATABASE;
import static com.expenditure.planner.Planner.LOGIN_DATABASE;
import static com.expenditure.planner.Planner.PASS_DATABASE;

public class JDBCPSQL {

    public void createUserPlansTable(String name) {
        String query = "CREATE TABLE " + name
                + "_PLANS (ID VARCHAR(128) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT);";
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Plans table for " + name + " was created");
    }

    public void createUserCashTable(String name) {
        String query = "CREATE TABLE " + name
                + "_CASH (ID VARCHAR(128) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Cash table for " + name + " was created");
    }

    public void createUserCardTable(String name) {
        String query = "CREATE TABLE " + name
                + "_CARD (ID VARCHAR(128) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        try {
            Connection connection = DriverManager.getConnection(URL_DATABASE, LOGIN_DATABASE, PASS_DATABASE);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Card table for " + name + " was created");
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

    public void appendPlans(ListPayments listPayments, String name) {
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

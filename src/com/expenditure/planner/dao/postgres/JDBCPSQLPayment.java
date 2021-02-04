package com.expenditure.planner.dao.postgres;

import com.expenditure.planner.Payment;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.sql.Connection;

import static com.expenditure.planner.Planner.DATABASE_URL;
import static com.expenditure.planner.Planner.DATABASE_LOGIN;
import static com.expenditure.planner.Planner.DATABASE_PASS;

public class JDBCPSQLPayment {

    Logger logger = Logger.getLogger(JDBCPSQLPayment.class.getName());

    public void savePayment(Payment payment) {

        String query = "INSERT INTO payments(NAME, VALUE) VALUES(?, ?)";
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, payment.getName());
            preparedStatement.setInt(1, payment.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePayment(List<Payment> listPayments, String listID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO payments (PAYMENT_ID, DESCRIPTION, PAYMENT_VALUE, LIST_ID) VALUES (?,?,?,?)";
        int i = 0;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(query);
            for (Payment payment : listPayments) {
                preparedStatement.setString(1, payment.getID().toString());
                preparedStatement.setString(2, payment.getName());
                preparedStatement.setInt(3, payment.getValue());
                preparedStatement.setString(4, listID);
                preparedStatement.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info(i + " Rows were appended");
    }

    public boolean connectCheck() {
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            Statement statement = connection.createStatement();
            if (connection != null) {
                logger.info("Connected to PostgreSQL server");
                flag = true;
            }
            ResultSet resultSet = statement.executeQuery("SELECT VERSION()");
            if (resultSet.next()) {
                logger.info(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public List<Payment> getAllPayments(String name) {
        List<Payment> listPayments = new ArrayList<>();
        String query = "SELECT * FROM PAYMENTS;";
        int i = 0;
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listPayments.add(new Payment(resultSet.getString(2), resultSet.getInt(3)));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info(i + " Rows were readed");
        return listPayments;
    }
}

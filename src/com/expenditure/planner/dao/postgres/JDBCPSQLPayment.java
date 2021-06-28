package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.DATABASE_URL;
import static com.expenditure.planner.Planner.DATABASE_NAME;
import static com.expenditure.planner.Planner.DATABASE_PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.expenditure.planner.Payment;

public class JDBCPSQLPayment {

    Logger logger = Logger.getLogger(JDBCPSQLPayment.class.getName());

    public void savePayment(Payment payment) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO payments(NAME, VALUE) VALUES(?, ?)";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, payment.getName());
            preparedStatement.setInt(1, payment.getValue());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveAllPayments(List<Payment> listPayments, String listID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO payments (PAYMENT_ID, DESCRIPTION, PAYMENT_VALUE, LIST_ID) VALUES (?,?,?,?)";
        int i = 0;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PASSWORD);
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
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info(i + " Rows were appended");
    }

    public List<Payment> getAllPayments(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Payment> listPayments = new ArrayList<>();
        String query = "SELECT * FROM PAYMENTS;";
        int i = 0;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listPayments.add(new Payment(resultSet.getString(2), resultSet.getInt(3)));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (resultSet != null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info(i + " Rows were readed");
        return listPayments;
    }
}

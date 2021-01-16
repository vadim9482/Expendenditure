package com.expenditure.planner.dao.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static com.expenditure.planner.Planner.DATABASE_URL;
import static com.expenditure.planner.Planner.DATABASE_LOGIN;
import static com.expenditure.planner.Planner.DATABASE_PASS;

import com.expenditure.planner.User;

public class JDBCPSQLUser {
    Logger logger = Logger.getLogger(JDBCPSQLUser.class.getName());

    public User getUser(String name) {
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM users WHERE NAME='" + name + "';";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            preparedStatement = connection.prepareStatement(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    private boolean isAvailabeUser(String name) {
        String query = "SELECT * FROM users WHERE NAME='" + name + "';";
        boolean flag = false;
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            flag = resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void saveUser(User user) {
        TableFactory.createUsersTable();
        TableFactory.createPlansTable(user.getName());
        TableFactory.createCashTable(user.getName());
        TableFactory.createCardTable(user.getName());
        String query = "INSERT INTO users (ID, NAME, PASSWORD) VALUES (?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
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

}

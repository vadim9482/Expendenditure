package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.DATABASE_URL;
import static com.expenditure.planner.Planner.ADMIN_NAME;
import static com.expenditure.planner.Planner.ADMIN_PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import com.expenditure.planner.User;
import com.expenditure.planner.UserFactory;

public class JDBCPSQLUser {
    Logger logger = Logger.getLogger(JDBCPSQLUser.class.getName());
    JDBCPSQLPayment jdbcpsqlPayment = new JDBCPSQLPayment();

    public void saveUser(User user) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT * FROM users WHERE NAME='" + user.getName() + "';";
        String query = "INSERT INTO users (USER_ID, NAME, PASSWORD) VALUES (?,?,?)";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
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
    }

    public Optional<User> getUser(String userName) {
        Optional<User> userOptional = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM users WHERE NAME='" + userName + "';";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String ID = resultSet.getString(1);
                String name = resultSet.getString(2);
                String password = resultSet.getString(3);
                userOptional = Optional.of(new UserFactory().createUser(ID, name, password));
            }
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
        return userOptional;
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        List<User> users = new LinkedList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String uuid = resultSet.getString("ID");
                String name = resultSet.getString("NAME");
                String password = resultSet.getString("PASSWORD");
                users.add(new UserFactory().createUser(uuid, name, password));
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
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}

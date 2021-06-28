package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.DATABASE_URL;
import static com.expenditure.planner.Planner.ADMIN_NAME;
import static com.expenditure.planner.Planner.ADMIN_PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.expenditure.planner.Table;

public class JDBCPSQLTables {
    Logger logger = Logger.getLogger(JDBCPSQLTables.class.getName());

    public void deleteTableUsers(Table table) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_database WHERE datname='" + table.getName() + "'";
        String query = "DROP TABLE " + table.getName();
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
            } else {
                logger.info("Table " + table.getName() + "doesn't exist");
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
    }

    public void createTableUsers(Table table) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_database WHERE datname='" + table.getName() + "'";
        String query = "CREATE TABLE " + table.getName()
                + " (USER_ID VARCHAR(36) NOT NULL UNIQUE, NAME VARCHAR(128) NOT NULL, PASSWORD VARCHAR(128) NOT NULL);";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                logger.info("Table " + table.getName() + " was created");
            } else {
                logger.info("Table " + table.getName() + " already exist");
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
    }
}

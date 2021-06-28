package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.DATABASE_URL;
import static com.expenditure.planner.Planner.ADMIN_NAME;
import static com.expenditure.planner.Planner.ADMIN_PASSWORD;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class TableFactory {
    public static Logger logger = Logger.getLogger(JDBCPSQLPayment.class.getName());

    public void createTableUsers(String table) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_tables WHERE tablename='" + table + "'";
        String query = "CREATE TABLE " + table
                + " (USER_ID VARCHAR(36) NOT NULL UNIQUE, NAME VARCHAR(128) NOT NULL, PASSWORD VARCHAR(128) NOT NULL);";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                logger.info("TABLE: " + table + " was created");
            } else {
                logger.info("TABLE: " + table + " already exist");
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

    public void deleteTableUsers() {
        String tableName = "users";
        deleteTable(tableName);
    }
    
    public void deleteTablePlans() {
        String tableName = "plans";
        deleteTable(tableName);
    }
    
    public void deleteTableCard() {
        String tableName = "card";
        deleteTable(tableName);
    }
    
    public void deleteTableCash() {
        String tableName = "cash";
        deleteTable(tableName);
    }

    public void createUsersTable() {
        String tableName = "users";
        String query = "CREATE TABLE " + tableName
                + " (USER_ID VARCHAR(36) NOT NULL UNIQUE, NAME VARCHAR(128) NOT NULL, PASSWORD VARCHAR(128) NOT NULL);";
        createTable(tableName, query);
    }

    public void createPlansTable() {
        String tableName = "plans";
        String query = "CREATE TABLE " + tableName
                + " (PAYMENT_ID VARCHAR(36) NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(128) NOT NULL, VALUE INT,  USER_ID VARCHAR(36) REFERENCES users(USER_ID) ON DELETE CASCADE);";
        createTable(tableName, query);
    }

    public void createCashTable() {
        String tableName = "cash";
        String query = "CREATE TABLE " + tableName
                + " (TRANSACTION_ID VARCHAR(128) NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(128) NOT NULL, VALUE INT, USER_ID VARCHAR(36) REFERENCES users(USER_ID) ON DELETE CASCADE, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    public void createCardTable() {
        String tableName = "card";
        String query = "CREATE TABLE " + tableName
                + " (TRANSACTION_ID VARCHAR(128) NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(128) NOT NULL, VALUE INT, USER_ID VARCHAR(36) REFERENCES users(USER_ID) ON DELETE CASCADE, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    private static void createTable(String tableName, String query) {
        Connection connection = null;
        DatabaseMetaData databaseMetaData = null;
        ResultSet resultSet = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                logger.info("TABLE: " + tableName + " already exist");
            } else {
                statement = connection.createStatement();
                statement.executeUpdate(query);
                logger.info("TABLE: " + tableName + " was created");
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.info(e.toString());
            }
        }
    }

    public void deleteTable(String tableName) {
        Connection connection = null;
        DatabaseMetaData databaseMetaData = null;
        ResultSet resultSet = null;
        Statement statement = null;
        String query = "DROP TABLE " + tableName + ";";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                statement = connection.createStatement();
                statement.executeUpdate(query);
                logger.info("TABLE: " + tableName + " was deleted");
            } else {
                logger.info("TABLE: " + tableName + " doesn't exist");
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.info(e.toString());
            }
        }
    }
}

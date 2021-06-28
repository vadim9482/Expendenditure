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
import java.util.logging.Logger;

public class TableFactory {
    public static Logger logger = Logger.getLogger(JDBCPSQLPayment.class.getName());

    public void createTableUsers() {
        String tableName = "users";
        String query = "CREATE TABLE " + tableName
                + " (USER_ID VARCHAR(36) NOT NULL UNIQUE, NAME VARCHAR(128) NOT NULL, PASSWORD VARCHAR(128) NOT NULL);";
        createTable(tableName, query);
    }

    public void createTablePlans() {
        String tableName = "plans";
        String query = "CREATE TABLE " + tableName
                + " (PAYMENT_ID VARCHAR(36) NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(128) NOT NULL, VALUE INT,  USER_ID VARCHAR(36) REFERENCES users(USER_ID) ON DELETE CASCADE);";
        createTable(tableName, query);
    }

    public void createTableCash() {
        String tableName = "cash";
        String query = "CREATE TABLE " + tableName
                + " (TRANSACTION_ID VARCHAR(128) NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(128) NOT NULL, VALUE INT, USER_ID VARCHAR(36) REFERENCES users(USER_ID) ON DELETE CASCADE, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    public void createTableCard() {
        String tableName = "card";
        String query = "CREATE TABLE " + tableName
                + " (TRANSACTION_ID VARCHAR(128) NOT NULL PRIMARY KEY, DESCRIPTION VARCHAR(128) NOT NULL, VALUE INT, USER_ID VARCHAR(36) REFERENCES users(USER_ID) ON DELETE CASCADE, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    private void createTable(String tableName, String query) {
        Connection connection = null;
        DatabaseMetaData databaseMetaData = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                logger.info("TABLE: " + tableName + " already exist");
            } else {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
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
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.info(e.toString());
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

    private void deleteTable(String tableName) {
        Connection connection = null;
        DatabaseMetaData databaseMetaData = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String query = "DROP TABLE " + tableName + ";";
        try {
            connection = DriverManager.getConnection(DATABASE_URL, ADMIN_NAME, ADMIN_PASSWORD);
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                logger.info("TABLE: " + tableName + " was deleted");
            } else {
                logger.info("TABLE: " + tableName + " doesn't exist");
            }
        } catch (SQLException e) {
         //   logger.info(e.toString());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
           //     logger.info(e.toString());
            }
        }
    }
}

package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.DATABASE_LOGIN;
import static com.expenditure.planner.Planner.DATABASE_PASS;
import static com.expenditure.planner.Planner.DATABASE_URL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class TableFactory {
    public static Logger logger = Logger.getLogger(JDBCPSQLPayment.class.getName());

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
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            databaseMetaData = connection.getMetaData();
            resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                logger.info("Table " + tableName + " already exist");
            } else {
                statement = connection.createStatement();
                statement.executeUpdate(query);
                logger.info("Table " + tableName + " was created");
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
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

    static void createUsersTable() {
        String tableName = "users";
        String query = "CREATE TABLE " + tableName
                + " (ID VARCHAR(36) NOT NULL, NAME VARCHAR(128) NOT NULL, PASSWORD VARCHAR(128) NOT NULL);";
        createTable(tableName, query);
    }

    static void createPlansTable(String name) {
        String tableName = "plans";
        String query = "CREATE TABLE " + tableName
                + " (USER_ID VARCHAR(36) NOT NULL, PAYMENT_ID VARCHAR(36) NOT NULL, DESCRIPTION VARCHAR(128) NOT NULL, VALUE INT);";
        createTable(tableName, query);
    }

    static void createCashTable(String name) {
        String tableName = "cash";
        String query = "CREATE TABLE " + tableName
                + " (ID VARCHAR(128) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    static void createCardTable(String name) {
        String tableName = "card";
        String query = "CREATE TABLE " + tableName
                + " (ID VARCHAR(128) NOT NULL, NAME VARCHAR(128) NOT NULL, VALUE INT, TRANSACTION_DATE DATE NOT NULL DEFAULT CURRENT_DATE);";
        createTable(tableName, query);
    }

    private static void createTable(String tableName, String query) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_LOGIN, DATABASE_PASS);
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, tableName, null);
            if (resultSet.next()) {
                logger.info("Table " + tableName + " already exist");
            } else {
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);
                logger.info("Table " + tableName + " was created");
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.info(e.toString());
            }
        }
    }

}
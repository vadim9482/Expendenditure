package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.SUPER_DATABASE_URL;
import static com.expenditure.planner.Planner.SUPER_DATABASE_NAME;
import static com.expenditure.planner.Planner.SUPER_DATABASE_PASS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.expenditure.planner.Admin;

public class JDBCPSQLDataBase {

    Logger logger = Logger.getLogger(JDBCPSQLDataBase.class.getName());

    public void deleteDataBase(String name, String password, Admin admin) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_database WHERE datname ='" + name + "'";
        String query = "SET ROLE " + admin.getName() + ";" + "DROP DATABASE " + name + ";";
        try {
            connection = DriverManager.getConnection(SUPER_DATABASE_URL, SUPER_DATABASE_NAME, SUPER_DATABASE_PASS);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                logger.info("DataBase " + name + " was deleted");
            }else {
                logger.info("DataBase " + name + " doesn't exist");
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

    public void saveDataBase(String name, String password, Admin admin) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_database WHERE datname ='" + name + "'";
        String query = "SET ROLE " + admin.getName() + ";" + "CREATE DATABASE " + name + " OWNER " + admin.getName()
                + ";";
        try {
            connection = DriverManager.getConnection(SUPER_DATABASE_URL, SUPER_DATABASE_NAME, SUPER_DATABASE_PASS);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                logger.info("DataBase " + name + " was saved");
            } else {
                logger.info("DataBase " + name + " already exist");
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

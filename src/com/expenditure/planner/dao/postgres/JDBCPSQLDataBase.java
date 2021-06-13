package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.ADMIN_DATABASE_LOGIN;
import static com.expenditure.planner.Planner.ADMIN_DATABASE_PASS;
import static com.expenditure.planner.Planner.ADMIN_DATABASE_URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JDBCPSQLDataBase {

    Logger logger = Logger.getLogger(JDBCPSQLDataBase.class.getName());

    public void saveDataBase(String name, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "CREATE USER " + name + "WITH " + "CREATEDB PASSWORD " + "'" + password + "';" + "SET ROLE "
                + name + ";" + "CREATE DATABASE " + "PAYMENTS" + name + " OWNER " + name + ";";
        try {
            connection = DriverManager.getConnection(ADMIN_DATABASE_URL, ADMIN_DATABASE_LOGIN, ADMIN_DATABASE_PASS);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (preparedStatement!=null)
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

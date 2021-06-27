package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.ADMIN_DATABASE_LOGIN;
import static com.expenditure.planner.Planner.ADMIN_DATABASE_PASS;
import static com.expenditure.planner.Planner.ADMIN_DATABASE_URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JDBCPSQLTables {
    Logger logger = Logger.getLogger(JDBCPSQLTables.class.getName());

    public void appendTableInfo(String ID, String name, String userID) {
        String query = "INSERT INTO tables(LIST_ID, DESCRIPTION, USER_ID) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(ADMIN_DATABASE_URL, ADMIN_DATABASE_LOGIN, ADMIN_DATABASE_PASS);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ID);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
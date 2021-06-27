package com.expenditure.planner.dao.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.expenditure.planner.Admin;
import static com.expenditure.planner.Planner.SUPER_ADMIN_URL;
import static com.expenditure.planner.Planner.SUPER_ADMIN_NAME;
import static com.expenditure.planner.Planner.SUPER_ADMIN_PASS;

public class JDBCPSQLAdmin {

    Logger logger = Logger.getLogger(JDBCPSQLAdmin.class.getName());

    public void saveAllAdmins(List<Admin> admins) {
        for (Admin admin : admins)
            saveAdmin(admin);
    }

    public void deleteAllAdmins(List<Admin> admins) {
        for (Admin admin : admins)
            deleteAdmin(admin);
    }

    public void saveAdmin(Admin admin) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_roles WHERE rolname='" + admin.getName() + "'";
        String query = "CREATE USER " + admin.getName() + " WITH CREATEDB PASSWORD '" + admin.getPassword() + "';";
        try {
            connection = DriverManager.getConnection(SUPER_ADMIN_URL, SUPER_ADMIN_NAME, SUPER_ADMIN_PASS);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                logger.info("Administrator: " + admin.getName() + " was saved");
            } else {
                logger.info("Administrator: " + admin.getName() + " already exist");
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
                    preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateAdmin(Admin admin, String[] params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_roles WHERE rolname='" + params[0] + "'";
        String query = "ALTER ROLE " + admin.getName() + " RENAME TO " + params[0] + "; " + "ALTER ROLE " + params[0]
                + " WITH PASSWORD '" + params[1] + "';";
        try {
            connection = DriverManager.getConnection(SUPER_ADMIN_URL, SUPER_ADMIN_NAME, SUPER_ADMIN_PASS);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                logger.info("Administrator: " + admin.getName() + " already renamed to " + params[0]);
            } else {
                logger.info("Administrator: " + params[0] + " already exist");
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

    public void deleteAdmin(Admin admin) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String checkQuery = "SELECT 1 FROM pg_roles WHERE rolname='" + admin.getName() + "'";
        String query = "SET ROLE postgres; DROP ROLE " + admin.getName() + ";";
        try {
            connection = DriverManager.getConnection(SUPER_ADMIN_URL, SUPER_ADMIN_NAME, SUPER_ADMIN_PASS);
            preparedStatement = connection.prepareStatement(checkQuery);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.executeUpdate();
                connection.close();
                preparedStatement.close();
                logger.info("Administrator: " + admin.getName() + " was deleted");
            } else {
                logger.info("Administrator: " + admin.getName() + " doesn't exist");
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

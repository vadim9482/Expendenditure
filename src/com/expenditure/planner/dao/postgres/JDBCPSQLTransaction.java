package com.expenditure.planner.dao.postgres;

import static com.expenditure.planner.Planner.DATABASE_URL;
import static com.expenditure.planner.Planner.DATABASE_NAME;
import static com.expenditure.planner.Planner.DATABASE_PASSWORD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.expenditure.planner.Transaction;

public class JDBCPSQLTransaction {
    
    Logger logger = Logger.getLogger(JDBCPSQLTransaction.class.getName());
    
    

    public void appendCash(List<Transaction> transactions) {
        String query = "INSERT INTO transactions (ID, NAME, PAYMENT_VALUE) VALUES (?,?,?)";
        int i = 0;
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_NAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (Transaction transaction : transactions) {
                preparedStatement.setString(1, transaction.getID().toString());
                preparedStatement.setString(2, transaction.getName());
                preparedStatement.setInt(3, transaction.getValue());
                preparedStatement.executeUpdate();
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info(i + " Rows were appended");
    }


}

package com.expenditure.planner;

import com.expenditure.planner.dao.csv.DAOCSVPayment;
import com.expenditure.planner.dao.postgres.JDBCPSQLPayment;
import com.expenditure.planner.dao.postgres.JDBCPSQLTables;
import com.expenditure.planner.dao.postgres.JDBCPSQLUser;

public class PlannerApp {

    public static void main(String[] args) {
        String plans = "plans";
        String userName = "Vadim";
        UserFactory userFactory = new UserFactory();
        User user = userFactory.createUser(userName, "pass");
        DAOCSVPayment daocsvPayment = new DAOCSVPayment();
        ListOfPayments listOfPayments = new ListOfPayments(plans);
        listOfPayments.add(daocsvPayment.getAll());
        user.addPaymentTable(listOfPayments);
        System.out.println(user.getPayments(plans));

        JDBCPSQLTables jdbcpsqlTables = new JDBCPSQLTables();
        JDBCPSQLPayment jdbcpsqlPayment = new JDBCPSQLPayment();
        JDBCPSQLUser jdbcpsqlUser = new JDBCPSQLUser();
        User userDB = jdbcpsqlUser.getUser(userName).get();
        jdbcpsqlTables.appendTableInfo(user.getPayments(plans).getUuid().toString(),
                user.getPayments(plans).getName(), userDB.getUuid().toString());
        // jdbcpsqlPayment.savePayment(user.getPayments(plans).getList(), plans);
    }
}

package com.expenditure.planner;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.csv.DAOCSVPayment;
import com.expenditure.planner.dao.csv.DAOCSVTransaction;
import com.expenditure.planner.formatter.Formater;
import com.expenditure.planner.formatter.FormaterPayments;

public class PlannerApp {

    public static void main(String[] args) {
        Planner planner = new Planner();
        User user = planner.createUser("Vadim", "pass");

        DAO<Payment> daoCSVPayment = new DAOCSVPayment();
        ListOfPayments tablePayments = new ListOfPayments("test");
        tablePayments.add(daoCSVPayment.getAll());
        user.addPlanTable(tablePayments);

        DAO<Transaction> daoTransaction = new DAOCSVTransaction();
        ListOfTransactions tableTransactions = new ListOfTransactions("cash");
        // tableTransactions.add

        // user.addCashTable(tableTransactions);

        Formater<Payment> formater = new FormaterPayments();
        System.out.println(formater.toString(user.getListOfPlans().get("test").getList()));
    }
}

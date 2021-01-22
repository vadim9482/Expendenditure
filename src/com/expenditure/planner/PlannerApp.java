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
        DAO<Transaction> daoTransaction = new DAOCSVTransaction();
        TablePayments tablePayments = new TablePayments("test");
        tablePayments.addPayment(daoCSVPayment.getAll());
        TableTransactions tableTransactions = new TableTransactions("cash");
        //tableTransactions.add
        user.addPlanTable(tablePayments);
        user.addCashTable(tableTransactions);
        
        Formater<Payment> formater = new FormaterPayments();
        System.out.println(formater.toString(user.getPlansTables().get(0).getListPayments()));
    }
}

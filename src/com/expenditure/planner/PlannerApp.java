package com.expenditure.planner;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.csv.DAOCSVPayment;
import com.expenditure.planner.dao.csv.DAOCSVTransaction;
import com.expenditure.planner.formatter.Formater;
import com.expenditure.planner.formatter.FormaterPayments;
import com.expenditure.planner.formatter.FormatterInt;

public class PlannerApp {

    public static void main(String[] args) {
        Planner planner = new Planner();
        User user = planner.createUser("Vadim", "pass");

        DAO<Payment> daoCSVPayment = new DAOCSVPayment();
        ListOfPayments listOfPayments = new ListOfPayments("plans");
        listOfPayments.add(daoCSVPayment.getAll());
        user.addPaymentTable(listOfPayments);

        DAO<Transaction> daoTransaction = new DAOCSVTransaction();
        ListOfTransactions listOfTransactions = new ListOfTransactions("cash");
        listOfTransactions.add(daoTransaction.getAll());
        user.addTransactionTable(listOfTransactions);

        Formater<Payment> formater = new FormaterPayments();
        Formater<Integer> formaterInt = new FormatterInt();
        ListOfPayments listOfPaymentsOut = user.getListOfPayments().get("plans");
        System.out.println(formater.listToString(listOfPaymentsOut.getList()));
        System.out.println(formaterInt.intToString("Summarry", listOfPaymentsOut.getSum()));
    }
}

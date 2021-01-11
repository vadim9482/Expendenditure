package com.expenditure.planner.dataCenter;

import com.expenditure.planner.ListTransaction;
import com.expenditure.planner.ListPayments;
import com.expenditure.planner.ParserPayments;
import com.expenditure.planner.ParserTransactions;
import com.expenditure.planner.Payment;
import com.expenditure.planner.Transaction;
import com.expenditure.planner.User;

public class DAO {

    public Transaction makeTransaction(Payment payment) {

        return new Transaction(payment);
    }

    public ListPayments importPaymentCsv(String filePath) {
        ListPayments listPayments = new ListPayments();
        String plansString = DataReader.read(filePath);
        ParserPayments parserPayments = new ParserPayments();
        listPayments.addPayment(parserPayments.parse(plansString));
        return listPayments;
    }

    public ListTransaction importCashCsv(String filepath) {
        ListTransaction listCash = new ListTransaction();
        String expencesString = DataReader.read(filepath);
        ParserTransactions parserTransactions = new ParserTransactions();
        listCash.addCash(parserTransactions.parse(expencesString));
        return listCash;
    }

    public void initUserDB(User user) {
        JDBCPSQL jdbcpsql = new JDBCPSQL();
        jdbcpsql.createUserPlansTable(user);
        jdbcpsql.createUserCashTable(user);
        jdbcpsql.createUserCardTable(user);
    }
}

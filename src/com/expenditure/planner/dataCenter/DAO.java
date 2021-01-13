package com.expenditure.planner.dataCenter;

import com.expenditure.planner.ListTransaction;

import java.util.LinkedList;
import java.util.List;

import com.expenditure.planner.ParserPayments;
import com.expenditure.planner.ParserTransactions;
import com.expenditure.planner.Payment;
import com.expenditure.planner.Transaction;

public class DAO {

    public Transaction makeTransaction(Payment payment) {

        return new Transaction(payment);
    }

    public List<Payment> importPaymentCsv(String resoursePath, String filename) {
        List<Payment> listPayments = new LinkedList<>();
        String plansString = DataReader.read(resoursePath, filename);
        ParserPayments parserPayments = new ParserPayments();
        listPayments.addAll(parserPayments.parse(plansString));
        return listPayments;
    }

    public ListTransaction importCashCsv(String resoursePath, String filename) {
        ListTransaction listCash = new ListTransaction();
        String expencesString = DataReader.read(resoursePath, filename);
        ParserTransactions parserTransactions = new ParserTransactions();
        listCash.addCash(parserTransactions.parse(expencesString));
        return listCash;
    }

    public void initUserDB(String ID, String name, String password) {
        JDBCPSQL jdbcpsql = new JDBCPSQL();
        jdbcpsql.addUser(ID, name, password);
        jdbcpsql.createUserPlansTable(name);
        jdbcpsql.createUserCashTable(name);
        jdbcpsql.createUserCardTable(name);
    }
}

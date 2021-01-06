package com.expenditure.planner.dataCenter;

import com.expenditure.planner.MoneyHolder;
import com.expenditure.planner.ListPayments;
import com.expenditure.planner.ParserPayments;
import com.expenditure.planner.ParserTransactions;
import com.expenditure.planner.Payment;
import com.expenditure.planner.Transaction;

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

    public MoneyHolder importCashCsv(String filepath) {
        MoneyHolder listCash = new MoneyHolder();
        String expencesString = DataReader.read(filepath);
        ParserTransactions parserTransactions = new ParserTransactions();
        listCash.addCash(parserTransactions.parse(expencesString));
        return listCash;
    }
}
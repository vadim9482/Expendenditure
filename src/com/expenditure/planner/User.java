package com.expenditure.planner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String password;
    private Map<String, ListOfPayments> paymentsLibrary;
    private Map<String, ListOfTransactions> transactionsLibrary;

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
        paymentsLibrary = new HashMap<>();
        transactionsLibrary = new HashMap<>();
    }

    User(String ID, String name, String password) {
        this.uuid = UUID.fromString(ID);
        this.name = name;
        this.password = password;
        paymentsLibrary = new HashMap<>();
        transactionsLibrary = new HashMap<>();
    }

    public boolean checkPassword(String inputPass) {
        return inputPass.equals(password);
    }

    public void addPaymentTable(ListOfPayments paymentsTable) {
        paymentsLibrary.put(paymentsTable.getName(), paymentsTable);
    }

    public void addTransactionTable(ListOfTransactions transactionsTable) {
        transactionsLibrary.put(transactionsTable.getName(), transactionsTable);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Map<String, ListOfPayments> getListOfPayments() {
        return paymentsLibrary;
    }

    public Map<String, ListOfTransactions> getListOfTransactions() {
        return transactionsLibrary;
    }

}

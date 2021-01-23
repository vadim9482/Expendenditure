package com.expenditure.planner;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String password;
    private Map<String, ListOfPayments> libraryPlans;
    private Map<String, ListOfTransactions> libraryCash;
    private Map<String, ListOfTransactions> libraryCard;

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
        libraryPlans = new HashMap<>();
        libraryCash = new HashMap<>();
        libraryCard = new HashMap<>();
    }

    User(String ID, String name, String password) {
        this.uuid = UUID.fromString(ID);
        this.name = name;
        this.password = password;
        libraryPlans = new HashMap<>();
        libraryCash = new HashMap<>();
        libraryCard = new HashMap<>();
    }

    public boolean checkPassword(String inputPass) {
        return inputPass.equals(password);
    }

    public void addPlanTable(ListOfPayments tablePayments) {
        libraryPlans.put(tablePayments.getName(), tablePayments);
    }

    public void addCashTable(ListOfTransactions tableTransactions) {
        libraryCash.put(tableTransactions.getName(), tableTransactions);
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

    public Map<String, ListOfPayments> getListOfPlans() {
        return libraryPlans;
    }

    public Map<String, ListOfTransactions> getListOfCash() {
        return libraryCash;
    }

    public Map<String, ListOfTransactions> getAllCard() {
        return libraryCard;
    }
}

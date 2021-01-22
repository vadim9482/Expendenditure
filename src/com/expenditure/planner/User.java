package com.expenditure.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID uuid;
    private String name;
    private String password;
    private List<TablePayments> plansTables;
    private List<TableTransactions> cashTables;
    private List<TableTransactions> cardTables;

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
        plansTables = new ArrayList<>();
        cashTables = new ArrayList<>();
        cardTables = new ArrayList<>();
    }

    User(String ID, String name, String password) {
        this.uuid = UUID.fromString(ID);
        this.name = name;
        this.password = password;
        plansTables = new ArrayList<>();
        cashTables = new ArrayList<>();
        cardTables = new ArrayList<>();
    }

    public boolean checkPassword(String inputPass) {
        return inputPass.equals(password);
    }

    public void addPlanTable(TablePayments tablePayments) {
        plansTables.add(tablePayments);
    }

    public void addCashTable (TableTransactions tableTransactions) {
        cashTables.add(tableTransactions);
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

    public List<TablePayments> getPlansTables() {
        return plansTables;
    }

    public List<TableTransactions> getCashTables() {
        return cashTables;
    }

    public List<TableTransactions> getCardTables() {
        return cardTables;
    }
}

package com.expenditure.planner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.expenditure.planner.dataCenter.DAO;

public class User {
    private UUID uuid;
    private String name;
    private String password;
    private List<Payment> plans;
    private List<Transaction> cash;
    private List<Transaction> card;
    DAO dao = new DAO();

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
        plans = new ArrayList<>();
        cash = new ArrayList<>();
        card = new ArrayList<>();
        dao.initUserDB(uuid.toString(), name, password);
    }

    User(String ID, String name, String password) {
        this.uuid = UUID.fromString(ID);
        this.name = name;
        this.password = password;
        plans = new ArrayList<>();
        cash = new ArrayList<>();
        card = new ArrayList<>();
        dao.initUserDB(uuid.toString(), name, password);
    }

    public void update() {

    }

    public boolean checkPassword(String inputPass) {
        return inputPass.equals(password);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<Payment> getListPlans() {
        return plans;
    }

    public List<Transaction> getListCash() {
        return cash;
    }

    public List<Transaction> getListCard() {
        return card;
    }

    public void addPlan(Payment payment) {
        plans.add(payment);
    }

    public void addPlan(List<Payment> listPayments) {
        plans.addAll(listPayments);
    }
}

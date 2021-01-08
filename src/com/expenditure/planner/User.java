package com.expenditure.planner;

import java.util.UUID;

import com.expenditure.planner.dataCenter.JDBCPSQL;

public class User {
    UUID uuid;
    String name;
    String password;
    ListPayments listPlans;
    ListTransaction listCash;
    ListTransaction listCard;

    User(String name, String password) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.password = password;
    }

    public void initUserDB() {
        listPlans = new ListPayments();
        listCash = new ListTransaction();
        listCard = new ListTransaction();
        JDBCPSQL jdbcpsql = new JDBCPSQL();
        jdbcpsql.createUserPlansTable(name);
        jdbcpsql.createUserCashTable(name);
        jdbcpsql.createUserCardTable(name);
    }
    
    public void refresh() {
        
    };

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ListPayments getList() {
        return listPlans;
    }
}

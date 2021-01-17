package com.expenditure.planner;

import com.expenditure.planner.dao.postgres.TableFactory;

public class PlannerApp {

    public static void main(String[] args) {
        TableFactory tableFactory = new TableFactory();
        tableFactory.createUsersTable();
        tableFactory.createPlansTable();
    }
}

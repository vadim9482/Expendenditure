package com.expenditure.planner;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOPayment;
import com.expenditure.planner.dao.csv.DAOCSVPayment;

public class PlannerApp {

    public static void main(String[] args) {
        DAO<Payment> daoCSVPayment = new DAOCSVPayment();
        DAO<Payment> daoPSQLPayment = new DAOPayment();
        daoPSQLPayment.saveAll(daoCSVPayment.getAll());
    }
}

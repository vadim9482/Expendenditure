package com.expenditure.planner;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.csv.DAOCSVPayment;
import com.expenditure.planner.formatter.Formater;
import com.expenditure.planner.formatter.FormaterPayments;

public class PlannerApp {

    public static void main(String[] args) {
        Planner planner = new Planner();
        planner.createUser("Vadim", "pass");
        User user = planner.readUser("Vadim");
        DAO<Payment> dao = new DAOCSVPayment();
        user.addPlan(dao.getAll());
        Formater<Payment> formater = new FormaterPayments();
        System.out.println(formater.toString(user.getPlans()));
    }
}

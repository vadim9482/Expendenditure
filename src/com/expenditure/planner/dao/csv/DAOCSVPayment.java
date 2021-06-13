package com.expenditure.planner.dao.csv;

import static com.expenditure.planner.Planner.FILENAME_PLANS;
import static com.expenditure.planner.Planner.RESOURCE_PATH;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Payment;
import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.csv.csvparser.ParserPayments;

public class DAOCSVPayment implements DAO<Payment> {

    @Override
    public Optional<Payment> get(String name) {
        return null;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> listPayments = new LinkedList<>();
        String plansString = FileToString.read(RESOURCE_PATH, FILENAME_PLANS);
        ParserPayments parserPayments = new ParserPayments();
        listPayments.addAll(parserPayments.parse(plansString));
        return listPayments;
    }

    @Override
    public void save(Payment t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveAll(List<Payment> t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Payment t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Payment t) {
        // TODO Auto-generated method stub

    }

}

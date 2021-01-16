package com.expenditure.planner.dao.csv;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Payment;
import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.csv.csvparser.ParserPayments;

public class CSVMethodsPayment implements DAO<Payment> {

    public List<Payment> importPaymentCsv(String resoursePath, String filename) {
        List<Payment> listPayments = new LinkedList<>();
        String plansString = FileToString.read(resoursePath, filename);
        ParserPayments parserPayments = new ParserPayments();
        listPayments.addAll(parserPayments.parse(plansString));
        return listPayments;
    }

    @Override
    public Optional<Payment> get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Payment> getAll() {
        // TODO Auto-generated method stub
        return null;
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

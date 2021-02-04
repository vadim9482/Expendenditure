package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Payment;
import com.expenditure.planner.dao.postgres.JDBCPSQLPayment;

public class DAOPayment implements DAO<Payment> {

    @Override
    public Optional<Payment> get(String ID) {
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
    public void saveAll(List<Payment> listPayment) {
        JDBCPSQLPayment jdbcpsqlPayment = new JDBCPSQLPayment();
        jdbcpsqlPayment.savePayment(listPayment, "1");
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

package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Transaction;

public class DAOTransation implements DAO<Transaction> {

    @Override
    public Optional<Transaction> get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(Transaction t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveAll(List<Transaction> t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Transaction t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Transaction t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(List<Transaction> t) {
        // TODO Auto-generated method stub

    }
}

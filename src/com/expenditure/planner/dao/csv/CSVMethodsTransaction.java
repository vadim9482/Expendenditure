package com.expenditure.planner.dao.csv;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Transaction;
import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.parser.ParserTransactions;

public class CSVMethodsTransaction implements DAO<Transaction> {

    public List<Transaction> importCashCsv(String resoursePath, String filename) {
        List<Transaction> listCash = new LinkedList<>();
        String expencesString = FileToString.read(resoursePath, filename);
        ParserTransactions parserTransactions = new ParserTransactions();
        listCash.addAll(parserTransactions.parse(expencesString));
        return listCash;
    }

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
    public void update(Transaction t, String[] params) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void delete(Transaction t) {
        // TODO Auto-generated method stub
        
    }

}

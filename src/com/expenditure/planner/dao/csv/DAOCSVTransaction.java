package com.expenditure.planner.dao.csv;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Transaction;
import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.csv.csvparser.ParserTransactions;

import static com.expenditure.planner.Planner.RESOURCE_PATH;
import static com.expenditure.planner.Planner.FILENAME_EXPENCES;

public class DAOCSVTransaction implements DAO<Transaction> {

    @Override
    public Optional<Transaction> get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> listCash = new LinkedList<>();
        String expencesString = FileToString.read(RESOURCE_PATH, FILENAME_EXPENCES);
        ParserTransactions parserTransactions = new ParserTransactions();
        listCash.addAll(parserTransactions.parse(expencesString));
        return listCash;
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

}

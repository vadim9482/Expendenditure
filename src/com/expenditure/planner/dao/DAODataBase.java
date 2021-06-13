package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.DataBase;

public class DAODataBase implements DAO<DataBase> {

    @Override
    public Optional<DataBase> get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DataBase> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(DataBase dataBase) {
        System.out.println(dataBase.getName() + " " + dataBase.getPassword());
    }

    @Override
    public void saveAll(List<DataBase> t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(DataBase t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(DataBase t) {
        // TODO Auto-generated method stub

    }

}

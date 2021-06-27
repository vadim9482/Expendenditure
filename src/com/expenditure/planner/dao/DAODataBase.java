package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.DataBase;
import com.expenditure.planner.dao.postgres.JDBCPSQLDataBase;

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
        JDBCPSQLDataBase jdbcpsqlDataBase = new JDBCPSQLDataBase();
        jdbcpsqlDataBase.saveDataBase(dataBase.getName(), dataBase.getPassword(), dataBase.getAdmin());
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
    public void delete(DataBase dataBase) {
        JDBCPSQLDataBase jdbcpsqlDataBase = new JDBCPSQLDataBase();
        jdbcpsqlDataBase.deleteDataBase(dataBase.getName(), dataBase.getPassword(), dataBase.getAdmin());
    }
}

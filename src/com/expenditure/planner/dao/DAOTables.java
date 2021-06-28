package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Table;
import com.expenditure.planner.dao.postgres.JDBCPSQLTables;

public class DAOTables implements DAO<Table> {

    JDBCPSQLTables jdbcpsqlTables = new JDBCPSQLTables();

    @Override
    public Optional<Table> get(String name) {
        return null;
    }

    @Override
    public List<Table> getAll() {
        return null;
    }

    @Override
    public void save(Table table) {
        jdbcpsqlTables.createTableUsers(table);
    }

    @Override
    public void saveAll(List<Table> t) {
    }

    @Override
    public void update(Table t, String[] params) {
    }

    @Override
    public void delete(Table table) {
        jdbcpsqlTables.deleteTableUsers(table);
    }
}

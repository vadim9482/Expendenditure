package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.User;
import com.expenditure.planner.dao.postgres.JDBCPSQLUser;

public class DAOUser implements DAO<User> {
    JDBCPSQLUser jdbcpsql = new JDBCPSQLUser();

    @Override
    public Optional<User> get(String name) {
        return null;
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(User t) {
        jdbcpsql.saveUser(t);
    }

    @Override
    public void saveAll(List<User> t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(User t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(User t) {
        // TODO Auto-generated method stub

    }

}

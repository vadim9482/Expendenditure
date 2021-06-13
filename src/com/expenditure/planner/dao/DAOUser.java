package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.User;
import com.expenditure.planner.dao.postgres.JDBCPSQLUser;

public class DAOUser implements DAO<User> {
    JDBCPSQLUser jdbcpsqlUser = new JDBCPSQLUser();

    @Override
    public Optional<User> get(String name) {
        return jdbcpsqlUser.getUser(name);
    }

    @Override
    public List<User> getAll() {
        return jdbcpsqlUser.getAllUsers();
    }

    @Override
    public void save(User user) {
        jdbcpsqlUser.saveUser(user);
    }

    @Override
    public void saveAll(List<User> t) {
    }

    @Override
    public void update(User t, String[] params) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(User t) {
    }

}

package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.User;
import com.expenditure.planner.dao.postgres.JDBCPSQLUser;

public class DAOUser implements DAO<User> {

    @Override
    public Optional<User> get(String name) {
        JDBCPSQLUser jdbcpsqlUser = new JDBCPSQLUser();
        return jdbcpsqlUser.getUser(name);
    }

    @Override
    public List<User> getAll() {
        JDBCPSQLUser jdbcpsqlUser = new JDBCPSQLUser();
        return jdbcpsqlUser.getAllUsers();
    }

    @Override
    public void save(User user) {
        JDBCPSQLUser jdbcpsqlUser = new JDBCPSQLUser();
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

    @Override
    public void deleteAll(List<User> t) {
        // TODO Auto-generated method stub
        
    }
}

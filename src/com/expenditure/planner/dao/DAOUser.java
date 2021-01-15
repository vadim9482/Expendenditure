package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.User;
import com.expenditure.planner.dao.postgres.JDBCPSQL;

public class DAOUser implements DAO<User> {
    JDBCPSQL jdbcpsql = new JDBCPSQL();
    
    @Override
    public Optional<User> get(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAll() {
                // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void save(User t) {
        jdbcpsql.addUser(t);
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

package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

import com.expenditure.planner.Admin;
import com.expenditure.planner.dao.postgres.JDBCPSQLAdmin;

public class DAOAdmin implements DAO<Admin> {

    @Override
    public Optional<Admin> get(String name) {
        return null;
    }

    @Override
    public List<Admin> getAll() {
        return null;
    }

    @Override
    public void save(Admin admin) {
        JDBCPSQLAdmin jdbcpsqlAdmin = new JDBCPSQLAdmin();
        jdbcpsqlAdmin.saveAdmin(admin);

    }

    @Override
    public void saveAll(List<Admin> admins) {
        JDBCPSQLAdmin jdbcpsqlAdmin = new JDBCPSQLAdmin();
        jdbcpsqlAdmin.saveAllAdmins(admins);
    }

    @Override
    public void update(Admin admin, String[] params) {
        JDBCPSQLAdmin jdbcpsqlAdmin = new JDBCPSQLAdmin();
        jdbcpsqlAdmin.updateAdmin(admin, params);
    }

    @Override
    public void delete(Admin admin) {
        JDBCPSQLAdmin jdbcpsqlAdmin = new JDBCPSQLAdmin();
        jdbcpsqlAdmin.deleteAdmin(admin);
    }

    @Override
    public void deleteAll(List<Admin> t) {
    }
}

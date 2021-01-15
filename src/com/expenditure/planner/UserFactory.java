package com.expenditure.planner;

import com.expenditure.planner.dao.DAO;
import com.expenditure.planner.dao.DAOUser;

public class UserFactory {

    public static User createUser(String name, String password) {
        User user = new User(name, password);
        DAO<User> dao = new DAOUser();
        dao.save(user);
        return user;
    }

}

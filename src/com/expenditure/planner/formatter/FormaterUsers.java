package com.expenditure.planner.formatter;

import java.util.List;
import java.util.UUID;

import com.expenditure.planner.User;

public class FormaterUsers implements Formater<User> {

    @Override
    public String toString(List<User> input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (User user : input) {
            stringBuilder.append(formatedView(user.getUuid(), user.getName()));
        }
        return stringBuilder.toString();
    }

    private String formatedView(UUID uuid, String name) {
        return String.format("| %-36s", uuid.toString()) +  String.format("| %14s |", name);
    }
}

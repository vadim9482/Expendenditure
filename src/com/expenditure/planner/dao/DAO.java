package com.expenditure.planner.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T>get(String name);
    List<T>getAll();
    void save(T t);
    void saveAll(List<T> t);
    void update (T t, String[] params);
    void delete (T t);
}

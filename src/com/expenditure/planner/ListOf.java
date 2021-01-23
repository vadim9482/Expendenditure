package com.expenditure.planner;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public abstract class ListOf<T> {
    private UUID uuid;
    private String name;
    private List<T> list;

    public ListOf(String name) {
        uuid = UUID.randomUUID();
        this.name = name;
        list = new LinkedList<T>();
    }

    public void add(T t) {
        list.add(t);
    }

    public void add(List<T> inputList) {
        list.addAll(inputList);
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<T> getList() {
        return list;
    }
    
}

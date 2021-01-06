package com.expenditure.planner;

import java.util.UUID;

public class User {
	UUID uuid;
	String name;
	String password;
	ListPayments listPayments;

	User(String name, String password) {
		this.uuid = UUID.randomUUID();
		this.name = name;
		this.password = password;
		listPayments = new ListPayments();
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public ListPayments getList() {
		return listPayments;
	}
}

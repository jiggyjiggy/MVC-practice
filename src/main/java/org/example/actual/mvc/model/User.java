package org.example.actual.mvc.model;

public class User {
	private final String userId;
	private final String name;
	
	public User(String userId, String name) {
		this.userId = userId;
		this.name = name;
	}
	
	public String getUserId() {
		return userId;
	}
}

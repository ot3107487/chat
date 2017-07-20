package com.yonder.chat;

public class User {
	private String username;
	private String password;
	private String message;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	
	
	public User(String username, String password, String message) {
		super();
		this.username = username;
		this.password = password;
		this.message = message;
	}



	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

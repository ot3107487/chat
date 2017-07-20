package com.yonder.chat;
public class User{
	private String name;
	private String mesaj;
	public User(String n,String m) {
		name=n;
		mesaj=m;
	}
	public String getName() {return name;}
	public String getMesaj() {return mesaj;}
}
package com.terrorbytes.scavengerscram.model;

public class UserLogin 
{
	private int id;
	private String name;
	private String authResult;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthResult() {
		return authResult;
	}
	public void setAuthResult(String authResult) {
		this.authResult = authResult;
	}
}

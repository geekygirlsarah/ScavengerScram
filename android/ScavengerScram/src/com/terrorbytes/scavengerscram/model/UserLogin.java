package com.terrorbytes.scavengerscram.model;

import java.io.Serializable;

import android.annotation.SuppressLint;

public class UserLogin  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -312500951829827145L;
	private int id;
	private String name;
	private String authResult;
	
	@SuppressLint("DefaultLocale")
	public boolean isValid()
	{
		return authResult != null && "pass".equals(authResult.toLowerCase());
	}
	
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

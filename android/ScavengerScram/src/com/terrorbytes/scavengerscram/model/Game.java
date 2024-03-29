package com.terrorbytes.scavengerscram.model;

import java.io.Serializable;
import java.util.Date;

public class Game implements Serializable{
private static final long serialVersionUID = -8410075313661737509L;
private int gameId;
	private String name;
	private String description;
	private String gameCode;
	private boolean locked;
	private Date startTime;
	private Date endTime;
	private String master;

	public Game(){}
	
	/**
	 * 
	 * @param gameId
	 * @param master
	 * @param name
	 * @param description
	 * @param gameCode
	 * @param locked
	 * @param startTime
	 * @param endTime
	 */
	public Game(int gameId, String master, String name, String description, 
			String gameCode, boolean locked, Date startTime, Date endTime) {
		super();
		this.gameId = gameId;
		this.name = name;
		this.description = description;
		this.gameCode = gameCode;
		this.locked = locked;
		this.startTime = startTime;
		this.endTime = endTime;
		this.master = master;
	}
	
	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}
	
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}

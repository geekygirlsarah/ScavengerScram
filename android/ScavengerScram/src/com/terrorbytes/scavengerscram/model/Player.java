package com.terrorbytes.scavengerscram.model;

public class Player {
  private int playerId;
	private String playerName;
	private String passWord;
	private String email;
	
	/**
	 * Default Constructor
	 */
	public Player(){
		
	}
	
	/**
	 * @param playerId
	 * @param playerName
	 * @param passWord
	 * @param email
	 */
	public Player(int playerId, String playerName, String passWord, String email) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.passWord = passWord;
		this.email = email;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}

package com.terrorbytes.scavengerscram.model;

public class Clue {
  private int clueId;
  	private int clueNumber;
	private String title;
	private String description;
	private int gameId;

	public Clue(){
		
	}

	/**
	 * 
	 * @param clueId
	 * @param clueNumber
	 * @param title
	 * @param description
	 * @param gameId
	 */
	public Clue(int clueId, int clueNumber, String title, String description, int gameId) {
		super();
		this.clueId = clueId;
		this.title = title;
		this.description = description;
		this.gameId = gameId;
		this.clueNumber = clueNumber;
	}
	
	public int getClueNumber() {
		return clueNumber;
	}

	public void setClueNumber(int clueNumber) {
		this.clueNumber = clueNumber;
	}


	/**
	 * @return the clueId
	 */
	public int getClueId() {
		return clueId;
	}

	/**
	 * @param clueId the clueId to set
	 */
	public void setClueId(int clueId) {
		this.clueId = clueId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the gameId
	 */
	public int getGameId() {
		return gameId;
	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	
	
}

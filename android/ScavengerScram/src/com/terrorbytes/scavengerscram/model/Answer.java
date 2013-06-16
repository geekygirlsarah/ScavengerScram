package com.terrorbytes.scavengerscram.model;

import java.util.Date;

import android.graphics.Bitmap;

public class Answer {

  private int answerId;
	private int clueId;
	private Date timeStamp;
	private Bitmap photo;
	private int playerId;
	private int gameId;
	private boolean correct;
	
	public Answer(){
		
	}

	/**
	 * @param answerId
	 * @param clueId
	 * @param timeStamp
	 * @param photo
	 * @param playerId
	 * @param gameId
	 * @param correct
	 */
	public Answer(int answerId, int clueId, Date timeStamp, Bitmap photo,
			int playerId, int gameId, boolean correct) {
		super();
		this.answerId = answerId;
		this.clueId = clueId;
		this.timeStamp = timeStamp;
		this.photo = photo;
		this.playerId = playerId;
		this.gameId = gameId;
		this.correct = correct;
	}

	/**
	 * @return the answerId
	 */
	public int getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
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
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the photo
	 */
	public Bitmap getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(Bitmap photo) {
		this.photo = photo;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
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

	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
	
}
